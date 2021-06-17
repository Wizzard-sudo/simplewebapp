package com.mastery.java.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyEmployeeNotFound extends RuntimeException{

    public MyEmployeeNotFound(Integer id) {
        super("Employee is not found, id=" + id);
    }
}
