package com.mastery.java.task.controllers;

import com.mastery.java.task.exceptions.DuplicateEmployeeException;
import com.mastery.java.task.exceptions.MyEmployeeNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class EmployeeExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateEmployeeException.class)
    public String duplicateEmployeeHandler(DuplicateEmployeeException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public String bindException(BindException e) {
        return collectResponseToInvalidRequest(e.getBindingResult());
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(MyEmployeeNotFound.class)
    public String notFound(MyEmployeeNotFound e) {
        return e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String defaultExceptionHandler(Exception e) {
        log.warn(e.getMessage());
        return "Internal error :" + e.getMessage();
    }

    public String collectResponseToInvalidRequest(BindingResult bindingResult) {
        String responseToInvalidRequest = "The request contains invalid data in the following fields :\n";
        for (Object object : bindingResult.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                responseToInvalidRequest += " Field - " + fieldError.getField() + "; Error - " + fieldError.getDefaultMessage() + ";\n";
            }
        }
        responseToInvalidRequest += "Please correct the request and repeat";
        return responseToInvalidRequest;
    }
}