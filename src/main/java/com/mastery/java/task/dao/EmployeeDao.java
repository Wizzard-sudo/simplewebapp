package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    public Employee getEmployeeByEmployeeId(int employeeId);

}
