package com.mastery.java.task.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public String handleError() {
        return "Custom Not Found (404)";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
