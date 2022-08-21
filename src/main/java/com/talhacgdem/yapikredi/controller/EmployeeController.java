package com.talhacgdem.yapikredi.controller;

import com.talhacgdem.yapikredi.exception.RequestVariableNotValidException;
import com.talhacgdem.yapikredi.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee API")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("leaveRequest")
    @ApiOperation(value = "Request a leave to manager by employee")
    public ResponseEntity requestLeave(
            @RequestParam("employeeId") @NotEmpty Long employeeId,
            @RequestParam("startDate") @NotEmpty @DateTimeFormat(pattern = "yyyy.MM.dd") Date startDate,
            @RequestParam("endDate") @NotEmpty @DateTimeFormat(pattern = "yyyy.MM.dd") Date endDate
    ) throws RequestVariableNotValidException {

        String response = employeeService.requestLeave(employeeId, startDate, endDate);
        return ResponseEntity.ok(response);
    }

}
