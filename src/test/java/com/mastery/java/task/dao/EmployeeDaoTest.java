//package com.mastery.java.task.dao;
//
//import com.mastery.java.task.dto.Employee;
//import com.mastery.java.task.dto.Gender;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.Date;
//import java.util.List;
//
//import static com.mastery.java.task.dto.Gender.MALE;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = TestConfig.class)
//@JdbcTest
//@Sql("/dbmigration/createTable.sql")
//@Sql(scripts = "/dbmigration/dropTable.sql", executionPhase = AFTER_TEST_METHOD)
//class EmployeeDaoTest {
//
//    @Autowired
//    EmployeeDaoImpl employeeDao;
//
//    @Autowired
//    JdbcTemplate template;
//
//    private  String querySelectAll = "SELECT * FROM employee";
//    private  String querySelectByEmployeeId = "SELECT * FROM employee where first_name='";
//    private  String queryEnd = "'";
//
//    private final int employeeId = 1;
//    private final String firstName = "Andy";
//    private final String firstNameUp = "Greg";
//    private final String lastName = "Samberg";
//    private final String lastNameUp = "Filch";
//    private final int departamentId = 1;
//    private final int departamentIdUp = 3;
//    private final String jobTitle = "Java Developer";
//    private final String jobTitleUp = "HR";
//    private final Gender gender = MALE;
//    private final Date date = java.sql.Date.valueOf("1989-03-02");
//
//    @Test
//    @Rollback
//    public void saveEmployeeTest() {
//        Employee employee = stubEmployee();
//        employeeDao.save(employee);
//
//        List<Employee> actualEmployeeList = template.query( querySelectByEmployeeId + employee.getFirstName()+queryEnd, new EmployeeMapper());
//
//        employee.setEmployeeId(actualEmployeeList.get(0).getEmployeeId());
//        assertThat(employee).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.get(0));
//    }
//
//    @Test
//    @Rollback
//    public void getByIdEmployeeTest(){
//        Employee employee = stubEmployee();
//        employeeDao.save(employee);
//
//        List<Employee> actualEmployeeList = template.query("SELECT * FROM employee where employee_id='" + employee.getEmployeeId()+queryEnd, new EmployeeMapper());
//
//        assertThat(employee).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.get(0));
//    }
//
//    @Test
//    @Rollback
//    public void getAllEmployeeTest(){
//        Employee employee = stubEmployee();
//        employeeDao.save(employee);
//        employee.setEmployeeId(2);
//        employeeDao.save(employee);
//        List<Employee> actualEmployeeList = template.query(querySelectAll, new EmployeeMapper());
//
//        assertThat(2).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.size());
//    }
//
//    @Test
//    @Rollback
//    public void deleteEmployeeByIdTest(){
//        Employee employee = stubEmployee();
//        employeeDao.save(employee);
//        List<Employee> actualEmployeeList = template.query(querySelectAll, new EmployeeMapper());
//        assertThat(1).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.size());
//        employeeDao.deleteById(employee.getEmployeeId());
//        actualEmployeeList = template.query("SELECT * FROM employee", new EmployeeMapper());
//        assertThat(0).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.size());
//    }
//
//    @Test
//    @Rollback
//    public void updateEmployeeTest(){
//        Employee employee = stubEmployee();
//        employeeDao.save(employee);
//        employee = updateEmployee(employee);
//        employeeDao.update(employee);
//        List<Employee> actualEmployeeList = template.query("SELECT * FROM employee where first_name='" + employee.getFirstName()+queryEnd, new EmployeeMapper());
//
//        assertThat(employee).isEqualToComparingFieldByFieldRecursively(actualEmployeeList.get(0));
//    }
//
//    private Employee stubEmployee() {
//        Employee employee = Employee.builder()
//                .employeeId(employeeId)
//                .firstName(firstName)
//                .lastName(lastName)
//                .departamentId(departamentId)
//                .jobTitle(jobTitle)
//                .gender(gender)
//                .dateOfBirth(date).build();
//        return employee;
//    }
//
//    private Employee updateEmployee(Employee employee){
//        employee.setFirstName(firstNameUp);
//        employee.setLastName(lastNameUp);
//        employee.setDepartamentId(departamentIdUp);
//        employee.setJobTitle(jobTitleUp);
//        employee.setGender(gender);
//        employee.setDateOfBirth(date);
//        return employee;
//    }
//}
