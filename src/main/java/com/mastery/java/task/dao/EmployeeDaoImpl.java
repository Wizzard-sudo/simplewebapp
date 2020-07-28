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

    private final DataSource dataSource;

    public EmployeeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(Employee employee) {
        String query = "INSERT INTO employee (first_name, last_name, departament_id, job_title, gender, date_of_birth) values (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getDepartamentId());
            preparedStatement.setString(4, employee.getJobTitle());
            //preparedStatement.setString(5, "MALE");
            preparedStatement.setString(5, String.valueOf(employee.getGender()));
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());

            int out = preparedStatement.executeUpdate();
            if (out != 0) {
                System.out.println("Employee saved with id=" + employee.getEmployeeId());
            } else System.out.println("Employee save failed with id=" + employee.getEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public Employee getById(int id) {

        String query = "SELECT employee_id, first_name, last_name, departament_id, job_title, gender, date_of_birth from employee where employee_id = ?";
        Employee employee = null;
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
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
                System.out.println("Employee Found::" + employee);
            } else {
                System.out.println("No Employee found with id=" + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employee;
    }


    public void update(Employee employee) {
        String query = "UPDATE employee set first_name=?, last_name=?, departament_id=?, job_title=?, gender=?, date_of_birth=? where employee_id=?";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getDepartamentId());
            preparedStatement.setString(4, employee.getJobTitle());
            preparedStatement.setString(5, String.valueOf(employee.getGender()));
            preparedStatement.setDate(6, (Date) employee.getDateOfBirth());
            preparedStatement.setInt(7, employee.getEmployeeId());
            int out = preparedStatement.executeUpdate();
            if (out != 0) {
                System.out.println("Update Employee with id=" + employee.getEmployeeId());
            } else System.out.println("Employee update failed with id=" + employee.getEmployeeId());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public void deleteById(int id) {
        String query = "DELETE from employee where employee_id=?";
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setLong(1, id);
            int out = preparedStatement.executeUpdate();
            if (out != 0) {
                System.out.println("Employee deleted with id=" + id);
            } else System.out.println("No Employee found with id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public List<Employee> getAll() {
        String query = "SELECT employee_id, first_name, last_name, departament_id, job_title, gender, date_of_birth from employee";
        List<Employee> employeeList = new ArrayList<Employee>();
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = dataSource.getConnection();
            preparedStatement = con.prepareStatement(query);
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
                System.out.println("Employee Found::" + employee);
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                preparedStatement.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return employeeList;
    }
}
