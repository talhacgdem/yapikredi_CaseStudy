package com.talhacgdem.yapikredi.service;

import com.talhacgdem.yapikredi.model.Employee;
import com.talhacgdem.yapikredi.model.Leave;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {

    private final LeaveService leaveService;
    private final EmployeeService employeeService;

    public ManagerService(LeaveService leaveService, EmployeeService employeeService) {
        this.leaveService = leaveService;
        this.employeeService = employeeService;
    }

    public List<Leave> getLeavesByPendingStatus() {
        return leaveService.getLeavesByPendingStatus();
    }


    public String approveLeaveFromId(Long leaveId) {
        return leaveService.approveLeaveFromId(leaveId);
    }


    public String rejectLeaveFromId(Long leaveId) {
        return leaveService.rejectLeaveFromId(leaveId);
    }


    public List<Employee> getEmployees() {
        return employeeService.getEmplooyes();
    }

}
