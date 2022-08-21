package com.talhacgdem.yapikredi.service;

import com.talhacgdem.yapikredi.exception.LeaveNotFoundException;
import com.talhacgdem.yapikredi.model.Employee;
import com.talhacgdem.yapikredi.model.Leave;
import com.talhacgdem.yapikredi.model.LeaveStatus;
import com.talhacgdem.yapikredi.repository.LeaveRepository;
import com.talhacgdem.yapikredi.service.LeaveService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.talhacgdem.yapikredi.config.Translator.toLocale;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }


    public String addLeaveRequest(Date startDate, Date endDate, Integer requestedDays, Employee employee) {
        Leave leave = new Leave();
        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setWorkDay(requestedDays);
        leave.setEmployee(employee);
        leaveRepository.save(leave);
        return toLocale("leaveRequestCreated");
    }


    public List<Leave> getLeavesByPendingStatus() {
        List<Leave> leaves = leaveRepository.findLeavesByStatus(String.valueOf(LeaveStatus.beklemede));
        return leaves;
    }


    public String approveLeaveFromId(Long leaveId) {

        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException(leaveId.toString()));

        leave.setStatus(String.valueOf(LeaveStatus.onayverildi));
        leaveRepository.save(leave);
        return toLocale("leaveApproved");
    }


    public String rejectLeaveFromId(Long leaveId) {
        Leave leave = leaveRepository.findById(leaveId)
                .orElseThrow(() -> new LeaveNotFoundException(leaveId.toString()));

        leave.setStatus(String.valueOf(LeaveStatus.reddedildi));
        leaveRepository.save(leave);
        return toLocale("leaveRejected");
    }


    public List<Leave> getLeavesByEmployeeId(Long employeeId) {
        return leaveRepository.findLeavesByEmployeeId(employeeId);
    }


    public Integer getUsedLeaveDaysByEmployee(Long employeeId) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date lastYear = cal.getTime();

        List<Leave> leaves = leaveRepository.findByStartDateAfterAndEmployeeId(lastYear, employeeId);
        return getUsedLeaveDays(leaves);
    }

    private Integer getUsedLeaveDays(List<Leave> leaves) {
        Integer usedDays = 0;
        for (int i = 0; i < leaves.size(); i++) {
            if (
                    leaves.get(i).getStatus().equalsIgnoreCase(String.valueOf(LeaveStatus.onayverildi)) ||
                            leaves.get(i).getStatus().equalsIgnoreCase(String.valueOf(LeaveStatus.beklemede))
            ) {
                usedDays = usedDays + leaves.get(i).getWorkDay();
            }
        }
        return usedDays;
    }


}
