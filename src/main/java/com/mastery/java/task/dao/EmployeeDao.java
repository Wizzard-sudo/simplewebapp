package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;

import java.util.List;


public interface EmployeeDao {

    public void save(Employee employee);

    public Employee getById(int id);

    public void update(Employee employee);

    public void deleteById(int id);

    public List<Employee> getAll();

}
