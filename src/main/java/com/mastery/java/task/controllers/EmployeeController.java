package com.mastery.java.task.controllers;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.service.EmployeeService;
import okhttp3.*;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final Log log = LogFactory.getLog(EmployeeController.class);
    private String logLevel = "INFO";

    @ModelAttribute("loglevel")
    public void addAttributeLogLevel(Model model){
        model.addAttribute("loglevel", logLevel);
    }

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        List<Employee> employees = employeeService.getAll();
        model.addAttribute("employees", employees);
        log.trace("This is a TRACE level message");
        log.debug("This is a DEBUG level message");
        log.info("This is an INFO level message");
        log.warn("This is a WARN level message");
        log.error("This is an ERROR level message");
        return "home";
    }

    @GetMapping("/add")
    public String employeeAdd(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-add";
    }

    @PostMapping("/add")
    public String employeeAdd(@Valid Employee employee, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            return "employee-add";
        }
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/get/{id}")
    public String employeeDetails(@PathVariable(value = "id") int id, Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee-details";
    }

    @PostMapping("/{id}/delete")
    public String employeeDelete(@PathVariable(value = "id") int id) {
        employeeService.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") int id, Model model) {

        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping("/{id}/edit")
    public String employeeUpdate(@PathVariable(value = "id") int id,
                                 @Valid Employee employee, BindingResult bindingResult, Model model) {
        employee.setEmployeeId(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("employee", employee);
            System.out.println(employee);
            return "employee-edit";
        }
        employeeService.update(employee);
        return "redirect:/";
    }

    @PostMapping("/lvl/{level}")
    public String changeLogLevel(@PathVariable(value = "level") String level) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"configuredLevel\": \"" + level + "\"}");
        Request request = new Request.Builder()
                .url("http://localhost:8080/actuator/loggers/com.mastery.java.task")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        logLevel = level;
        System.out.println(level);
        return "redirect:/";
    }
}