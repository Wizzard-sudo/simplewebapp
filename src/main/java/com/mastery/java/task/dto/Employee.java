package com.mastery.java.task.dto;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "data model of employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer departamentId;
    private String jobTitle;
    private String gender;
    private Date dateOfBirth;

}
