package com.mastery.java.task.dto;

import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Builder
@ToString
public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private int departamentId;
    private String jobTitle;
    private Gender gender;
    private Date dateOfBirth;

    public Employee() {
    }

    public Employee(int employeeId, String firstName, String lastName, int departamentId, String jobTitle, Gender gender, Date dateOfBirth) {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartamentId() {
        return departamentId;
    }

    public void setDepartamentId(int departamentId) {
        this.departamentId = departamentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Gender getGender() {
        return gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
