package com.mastery.java.task.controllers;

import com.mastery.java.task.exceptions.DuplicateEmployeeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {

    @ExceptionHandler(DuplicateEmployeeException.class)
    public ResponseEntity<String> duplicateEmployeeHandler(DuplicateEmployeeException exception) {
        return ResponseEntity.status(400).body(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity defaultExceptionHandler(Exception e) {
        log.warn(e.getMessage());
        return ResponseEntity.status(500).body("Internal error :" + e.getMessage());
    }
}