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
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("Employee rest")
@AllArgsConstructor
@Slf4j
public class EmployeeRestController {

    private final EmployeeService employeeService;
    private final JmsTemplate jmsTemplate;
    private final Queue queueDelete;


    @GetMapping("/employees")
    @ApiOperation("method to get all employees")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeService.getAll();
        log.info("Getting all employees from the database. Employee count: {}", employees.size());
        return employees;
    }

    @GetMapping("/employee/{id}")
    @ApiOperation("method to get employee by id")
    @ApiResponse(code = 404, message = "Not Found")
    @ResponseStatus(code = HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getById(id);
        log.info("Employee with ID {} received: {}", id, employee);
        return employee;
    }

    @PostMapping("/employee")
    @ApiOperation("method to add employee")
    @ApiResponses({@ApiResponse(code = 201, message = "Employee created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not Found")})
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@Valid Employee employee) throws DuplicateEmployeeException {
        employeeService.save(employee);
        log.info("Added employee: {}", employee);
        return employee;
    }

    @PutMapping("/employee/{id}")
    @ApiOperation("method to update employee")
    @ResponseStatus(code = HttpStatus.CREATED)
    @ApiResponse(code = 404, message = "Not Found")
    public Employee updateEmployee(@PathVariable("id") Integer id, @Valid Employee employee) {
        employeeService.getById(id);
        employee.setEmployeeId(id);
        employeeService.update(employee);
        log.info("Employee with ID {} changed: {}", id, employee);
        return employee;
    }

    @DeleteMapping("/employee/{id}")
    @ApiOperation("method to delete employee by id")
    @ApiResponses({@ApiResponse(code = 204, message = "Employee deleted"), @ApiResponse(code = 404, message = "Not Found")})
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.getById(id);
        this.jmsTemplate.convertAndSend(this.queueDelete, String.valueOf(id));
        log.info("Employee with ID {} deleted", id);
    }
}