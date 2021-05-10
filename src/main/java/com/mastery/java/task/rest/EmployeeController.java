package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Controller
public class EmployeeController {


    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        Iterable<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        return "home";
    }

    @GetMapping("/add")
    public String employeeAdd(Model model) {
        return "employee-add";
    }

    @PostMapping("/add")
    public String employeeAdd(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam int departamentId,
                              @RequestParam String jobTitle,
                              @RequestParam String gender,
                              @RequestParam Date dateOfBirth, Model model) {
        Employee emp = Employee.builder()
                .firstName(firstName)
                .lastName(lastName)
                .departamentId(departamentId)
                .jobTitle(jobTitle)
                .gender(gender)
                .dateOfBirth(dateOfBirth).build();
        employeeService.save(emp);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String employeeDetails(@PathVariable(value = "id") int id, Model model) {

        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee-details";
    }

    @PostMapping("/{id}/delete")
    public String employeeDelete(@PathVariable(value = "id") int id, Model model) {

        employeeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") int id, Model model) {

        Employee employee = employeeService.getById(id);
        System.out.println(employee.toString());
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping("/{id}/edit")
    public String employeeUpdate(@PathVariable(value = "id") int id,
                                 @RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int departamentId,
                                 @RequestParam String jobTitle,
                                 @RequestParam String gender,
                                 @RequestParam Date dateOfBirth, Model model) {
        Employee employee = employeeService.getById(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartamentId(departamentId);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(dateOfBirth);
        employeeService.update(employee);
        return "redirect:/";
    }

}