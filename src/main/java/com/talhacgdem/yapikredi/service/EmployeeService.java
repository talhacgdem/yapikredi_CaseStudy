package com.talhacgdem.yapikredi.service;


import com.talhacgdem.yapikredi.exception.DateRangeException;
import com.talhacgdem.yapikredi.model.Employee;
import com.talhacgdem.yapikredi.service.EmployeeService;
import com.talhacgdem.yapikredi.repository.EmployeeRepository;

import com.talhacgdem.yapikredi.model.PublicHoliday;
import com.talhacgdem.yapikredi.service.LeaveService;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kong.unirest.GenericType;
import kong.unirest.Unirest;


import org.springframework.stereotype.Service;

import com.talhacgdem.yapikredi.exception.EmployeeNotFoundException;
import com.talhacgdem.yapikredi.exception.ExceedLeaveRightsException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final LeaveService leaveService;

    public EmployeeService(EmployeeRepository employeeRepository, LeaveService leaveService) {
        this.employeeRepository = employeeRepository;
        this.leaveService = leaveService;
    }


    public List<Employee> getEmplooyes() {
        return employeeRepository.findAll();
    }


    public String requestLeave(Long employeeId, Date startDate, Date endDate) {

        if (startDate.after(endDate)){
            throw new DateRangeException();
        }


        Integer requestedDays = null;
        try {
            requestedDays = calculateWorkDays(startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId.toString()));

        Integer usedRights = leaveService.getUsedLeaveDaysByEmployee(employeeId);
        Integer maxRights = getTotalLeaveRights(employee.getWorkStartDate());
        Integer currentRight = maxRights - usedRights;

        if (requestedDays > currentRight)
            throw new ExceedLeaveRightsException();


        return leaveService.addLeaveRequest(startDate, endDate, requestedDays, employee);


    }


    public Integer calculateWorkDays(Date startDate, Date endDate) throws ParseException {
        int workDays = 0;

        Calendar startDateCal = Calendar.getInstance();
        startDateCal.setTime(startDate);

        Calendar endDateCal = Calendar.getInstance();
        endDateCal.setTime(endDate);

        if (startDateCal.getTimeInMillis() == endDateCal.getTimeInMillis()) {
            return 0;
        }

        List<Integer> holidayListDayOfYear = getOfficalHolidays();

        while (startDateCal.getTimeInMillis() < endDateCal.getTimeInMillis()) {
            startDateCal.add(Calendar.DAY_OF_MONTH, 1);
            if (
                    startDateCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY &&
                    startDateCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                    !holidayListDayOfYear.contains((Integer) startDateCal.get(Calendar.DAY_OF_YEAR))
            ) {
                workDays++;
            }
        }
        return workDays;
    }

    public List<Integer> getOfficalHolidays() throws ParseException {

        List<PublicHoliday> publicHoliday = Unirest.get("https://date.nager.at/api/v2/publicholidays/"
                        + String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "/tr")
                .asObject(new GenericType<List<PublicHoliday>>() {
                }).getBody();
        List<Integer> holidayListDayOfYear = new ArrayList<Integer>();

        for (int i = 0; i < publicHoliday.size(); i++) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(publicHoliday.get(i).getDate());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            holidayListDayOfYear.add(calendar.get(Calendar.DAY_OF_YEAR));
        }
        return holidayListDayOfYear;
    }


    public Integer getTotalLeaveRights(Date startDate) {

        long diffInMillies = Math.abs(new Date().getTime() - startDate.getTime());
        long difference_In_Days = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        Integer years = (int) (difference_In_Days / 365);

        if (years < 1) {
            return 5;
        } else if (1 <= years && years <= 5) {
            return (years) * 15;
        } else if (5 < years && years <= 10) {
            return (5 * 15) + ((years - 5) * 18);
        } else {
            return (5 * 15) + (5 * 18) + ((years - 10) * 24);
        }

    }


}
