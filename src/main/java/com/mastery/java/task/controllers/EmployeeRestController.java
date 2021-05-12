package com.mastery.java.task.controllers;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("Employee rest")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    @ApiOperation("method to get all employees")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getById")
    @ApiOperation("method to get employee bu id")
    public ResponseEntity<Employee> getEmployeeById(@RequestParam("id") Integer id){
        Employee employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/add")
    @ApiOperation("method to add employee")
    public ResponseEntity<Employee> addEmployee(@RequestParam("firstName") String firstName,
                                                             @RequestParam("lastName") String lastName,
                                                             @RequestParam("departamentId") int departamentId,
                                                             @RequestParam("jobTitle") String jobTitle,
                                                             @RequestParam("gender") String gender,
                                                             @RequestParam("dateOfBirth") Date dateOfBirth){
        Employee employee = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .departamentId(departamentId)
                .jobTitle(jobTitle)
                .gender(gender)
                .dateOfBirth(dateOfBirth).build();
        employeeService.save(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/update")
    @ApiOperation("method to update employee")
    public ResponseEntity<Employee> updateEmployee(@RequestParam("id") Integer id,
                                                                @RequestParam("firstName") String firstName,
                                                                @RequestParam("lastName") String lastName,
                                                                @RequestParam("departamentId") int departamentId,
                                                                @RequestParam("jobTitle") String jobTitle,
                                                                @RequestParam("gender") String gender,
                                                                @RequestParam("dateOfBirth") Date dateOfBirth){
        Employee employee = Employee.builder()
                .employeeId(id)
                .firstName(firstName)
                .lastName(lastName)
                .departamentId(departamentId)
                .jobTitle(jobTitle)
                .gender(gender)
                .dateOfBirth(dateOfBirth).build();
        employeeService.save(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/delete")
    @ApiOperation("method to delete employee by id")
    public ResponseEntity<Employee> deleteEmployee(@RequestParam("id") Integer id){
        Employee employee = employeeService.getById(id);
        employeeService.deleteById(id);
        return ResponseEntity.ok(employee);
    }
}