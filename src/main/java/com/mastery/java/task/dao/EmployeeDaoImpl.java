package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private final String querySave = "INSERT INTO employee (first_name, last_name, departament_id, job_title, gender, date_of_birth) values (?, ?, ?, ?, ?, ?)";
    private final String queryGetById = "SELECT * from employee where employee_id = ?";
    private final String queryUpdate = "UPDATE employee set first_name=?, last_name=?, departament_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=?";
    private final String queryGetAll = "SELECT * from employee";
    private final String queryDeleteById = "DELETE from employee where employee_id=?";


    private final DataSource dataSource;

    public EmployeeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(Employee employee) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(querySave);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getDepartamentId());
            preparedStatement.setString(4, employee.getJobTitle());
            preparedStatement.setString(5, String.valueOf(employee.getGender()));
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error", e);
            }
        }

    }


    public Employee getById(int id) {

        Employee employee = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(queryGetById);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setDepartamentId(resultSet.getInt("departament_id"));
                employee.setJobTitle(resultSet.getString("job_title"));
                employee.setGender(Gender.valueOf(resultSet.getString("gender")));
                employee.setDateOfBirth(resultSet.getDate("date_of_birth"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error", e);
            }
        }
        return employee;
    }


    public void update(Employee employee) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(queryUpdate);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getDepartamentId());
            preparedStatement.setString(4, employee.getJobTitle());
            preparedStatement.setString(5, String.valueOf(employee.getGender()));
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());
            preparedStatement.setInt(7, employee.getEmployeeId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error", e);
            }
        }
    }


    public void deleteById(int id) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(queryDeleteById);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error", e);
            }
        }
    }


    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(queryGetAll);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setDepartamentId(resultSet.getInt("departament_id"));
                employee.setJobTitle(resultSet.getString("job_title"));
                employee.setGender(Gender.valueOf(resultSet.getString("gender")));
                employee.setDateOfBirth(resultSet.getDate("date_of_birth"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error", e);
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException("Error", e);
            }
        }
        return employeeList;
    }
}
