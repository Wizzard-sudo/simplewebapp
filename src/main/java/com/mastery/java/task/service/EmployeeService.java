package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    private final EmployeeDao employeeDao;

    public void save(Employee employee) {
        employeeDao.save(employee);
    }


    public Employee getById(int id) {
        return employeeDao.getEmployeeByEmployeeId(id);
    }


    public void update(Employee employee) {
        employeeDao.save(employee);
    }


    public void deleteById(int id) {
        employeeDao.deleteById(id);
    }


    public List<Employee> getAll() {
        return employeeDao.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
    }
}
