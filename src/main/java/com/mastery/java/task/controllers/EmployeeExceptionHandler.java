package com.mastery.java.task.controllers;

import com.mastery.java.task.exceptions.DuplicateEmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmployeeException.class)
    public String duplicateEmployeeHandler(DuplicateEmployeeException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Exception e) {
        log.warn(e.getMessage());
        return "Internal error :" + e.getMessage();
    }

}