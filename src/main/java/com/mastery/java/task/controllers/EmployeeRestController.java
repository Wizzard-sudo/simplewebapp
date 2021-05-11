package com.mastery.java.task.controllers;

import com.mastery.java.task.dto.ApiResponse;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<Employee>> getAllEmployee(){
        ApiResponse<Employee>response = new ApiResponse<>();
        List<Employee> employees = employeeService.getAll();
        response.setDebugMessage("successful request");
        response.setMessage("data size: " + employees.size() + " elements");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getById")
    public ResponseEntity<ApiResponse<Employee>> getEmployeeById(@RequestParam("id") Integer id){
        ApiResponse<Employee>response = new ApiResponse<>();
        Employee employee = employeeService.getById(id);
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        response.setDebugMessage("successful request");
        response.setMessage("employee with this id was found");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/add")
    public ResponseEntity<ApiResponse<Employee>> addEmployee(@RequestParam("firstName") String firstName,
                                                             @RequestParam("lastName") String lastName,
                                                             @RequestParam("departamentId") int departamentId,
                                                             @RequestParam("jobTitle") String jobTitle,
                                                             @RequestParam("gender") String gender,
                                                             @RequestParam("dateOfBirth") Date dateOfBirth){
        ApiResponse<Employee>response = new ApiResponse<>();
        Employee emp = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .departamentId(departamentId)
                .jobTitle(jobTitle)
                .gender(gender)
                .dateOfBirth(dateOfBirth).build();
        employeeService.save(emp);
        List<Employee> employees = new ArrayList<>();
        employees.add(emp);
        response.setDebugMessage("successful request");
        response.setMessage("employee was added successfully");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/update")
    public ResponseEntity<ApiResponse<Employee>> updateEmployee(@RequestParam("id") Integer id,
                                                                @RequestParam("firstName") String firstName,
                                                                @RequestParam("lastName") String lastName,
                                                                @RequestParam("departamentId") int departamentId,
                                                                @RequestParam("jobTitle") String jobTitle,
                                                                @RequestParam("gender") String gender,
                                                                @RequestParam("dateOfBirth") Date dateOfBirth){
        ApiResponse<Employee>response = new ApiResponse<>();
        Employee emp = Employee.builder()
                .employeeId(id)
                .firstName(firstName)
                .lastName(lastName)
                .departamentId(departamentId)
                .jobTitle(jobTitle)
                .gender(gender)
                .dateOfBirth(dateOfBirth).build();
        employeeService.save(emp);
        List<Employee> employees = new ArrayList<>();
        employees.add(emp);
        response.setDebugMessage("successful request");
        response.setMessage("employee with the id " + id + " was successfully updated");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(employees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/delete")
    public ResponseEntity<ApiResponse<Employee>> deleteEmployee(@RequestParam("id") Integer id){
        ApiResponse<Employee>response = new ApiResponse<>();
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeService.getById(id));
        employeeService.deleteById(id);
        response.setDebugMessage("successful request");
        response.setMessage("employee with the id " + id + " was successfully deleted");
        response.setStatus(HttpStatus.OK);
        response.setTimeStamp(LocalDateTime.now());
        response.setData(employees);
        return ResponseEntity.ok(response);
    }
}
