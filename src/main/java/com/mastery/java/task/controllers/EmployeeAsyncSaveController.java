package com.mastery.java.task.controllers;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exceptions.DuplicateEmployeeException;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@Api("Employee Async Save")
@AllArgsConstructor
@Slf4j
public class EmployeeAsyncSaveController {

    private final EmployeeService employeeService;
    private final JmsTemplate jmsTemplate;
    private final Queue queueSave;

    @PostMapping("/employeeAsync")
    @ApiOperation("method to add employee (async)")
    @ApiResponses({@ApiResponse(code = 201, message = "Employee created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee saveAsyncEmployee(@Valid Employee employee) throws DuplicateEmployeeException {
        if(employeeService.getExistingEmployeeId(employee) != null){
            throw new DuplicateEmployeeException(employee, employeeService.getExistingEmployeeId(employee), "Error: Duplicate user. A user with such data exists - id " + employeeService.getExistingEmployeeId(employee));
        }else {
            this.jmsTemplate.convertAndSend(this.queueSave, employee);
            log.info("Added employee: {}", employee);
            return employee;
        }
    }
}
