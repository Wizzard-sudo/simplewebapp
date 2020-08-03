package com.mastery.java.task.dto;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int employeeId;
    private String firstName;
    private String lastName;
    private int departamentId;
    private String jobTitle;
    private Gender gender;
    private Date dateOfBirth;
}
