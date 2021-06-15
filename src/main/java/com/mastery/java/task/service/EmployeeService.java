package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exceptions.DuplicateEmployeeException;
import org.springframework.data.domain.Sort;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeService {

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    private final EmployeeDao employeeDao;

    @Transactional
    public void save(Employee employee) throws DuplicateEmployeeException {
        if (getExistingEmployeeId(employee) != null) {
            throw new DuplicateEmployeeException(employee, getExistingEmployeeId(employee), "Error: Duplicate user. A user with such data exists - id " + getExistingEmployeeId(employee));
        } else {
                employeeDao.save(employee);
            }
        }


    private Integer getExistingEmployeeId(Employee employee) {
        return employeeDao.getExistingEmployee(employee.getFirstName(),
                employee.getLastName(), employee.getDepartamentId(), employee.getJobTitle(), employee.getGender(), employee.getDateOfBirth());
    }

    public Employee getById(int id) {
        return employeeDao.findById(id).get();
    }


    public void update(Employee employee) {
        employeeDao.save(employee);
    }


    @JmsListener(destination = "simplewebapp.queue")
    public void deleteById(int id) {
        System.out.println(id);
        employeeDao.deleteById(id);
    }


    public List<Employee> getAll() {
        return employeeDao.findAll(Sort.by(Sort.Direction.ASC, "employeeId"));
    }
}
