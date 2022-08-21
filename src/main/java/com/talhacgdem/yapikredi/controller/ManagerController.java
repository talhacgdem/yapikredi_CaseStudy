package com.talhacgdem.yapikredi.controller;

import com.talhacgdem.yapikredi.config.Translator;
import com.talhacgdem.yapikredi.model.Employee;
import com.talhacgdem.yapikredi.model.Leave;
import com.talhacgdem.yapikredi.service.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.talhacgdem.yapikredi.config.Translator.toLocale;

@RestController
@RequestMapping("/manager")
@Api(value = "Manager API")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/listpendings")
    @ApiOperation(value = "List all leaves on pending")
    public ResponseEntity getPendingLeaves() {
        List<Leave> leaves = managerService.getLeavesByPendingStatus();
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }

    @GetMapping("/employees")
    @ApiOperation(value = "List all employees")
    public ResponseEntity getEmployees(){
        List<Employee> employees = managerService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/approve")
    @ApiOperation(value = "Update a leave status to approve from id")
    public ResponseEntity approveLeave(@RequestParam(name = "leaveId") Long leaveId){
        String response = managerService.approveLeaveFromId(leaveId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reject")
    @ApiOperation(value = "Update a leave status to reject from id")
    public ResponseEntity rejectLeaveFromId(@RequestParam(name = "leaveId") Long leaveId){
        String response = managerService.rejectLeaveFromId(leaveId);
        return ResponseEntity.ok(response);
    }


}
