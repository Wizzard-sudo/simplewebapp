package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;


public class EmployeeServiceTest {

    private List<Employee> employeeList;
    private final int employeeId = 1;
    private final String firstName = "Andy";
    private final String lastName = "Samberg";
    private final int departamentId = 1;
    private final String jobTitle = "Java Developer";
    private final Gender gender = MALE;
    private final String datestr = "1989-03-02";
    private final Date date = java.sql.Date.valueOf(datestr);

    @Mock
    EmployeeDaoImpl employeeDao;
    @InjectMocks
    EmployeeService employeeService;


    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        List<Employee> employeeList = stubEmployeeList();

        when(employeeDao.getAll()).thenReturn(employeeList);

        List<Employee> empList = employeeService.getAll();

        assertEquals( 3, empList.size());
        then(employeeDao).should().getAll();
    }

    @Test
    public void getByIdTest(){
        Employee emp = stubEmployee();

        when(employeeDao.getById(employeeId)).thenReturn(emp);

        Employee employee = employeeService.getById(employeeId);

        assertEquals( employeeId, employee.getEmployeeId());
        assertEquals( firstName, employee.getFirstName());
        assertEquals( lastName, employee.getLastName());
        assertEquals( departamentId, employee.getDepartamentId());
        assertEquals( gender, employee.getGender());
        assertEquals( date, employee.getDateOfBirth());

        then(employeeDao).should().getById(employeeId);
    }

    @Test
    public void saveTest(){

        Employee emp = stubEmployee();

        employeeService.save(emp);
        then(employeeDao).should().save(emp);
    }

    @Test
    public void deleteByIdTest(){
        int id = 1;

        employeeService.deleteById(id);

        then(employeeDao).should().deleteById(id);
    }

    @Test
    public void updateTest(){

        Employee emp = stubEmployee();
        employeeService.update(emp);

        then(employeeDao).should().update(emp);
    }

    private List<Employee> stubEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();

        int[] employeeId = new int[]{1, 2, 3};
        String[] firstName = new String[]{"Andy", "Joe Lo", "Chelsea"};
        String[] lastName = new String[]{"Samberg", "Truglio", "Peretti"};
        int[] departamentId = new int[]{1, 2, 3};
        String[] jobTitle = new String[]{"Java Developer", "HR", "Tester"};
        Gender[] gender = new Gender[]{MALE, MALE, FEMALE};
        Date[] date = new Date[3];
        String[] datestr = new String[]{"1989-03-02", "1993-06-05", "1995-08-07"};
        for (int i = 0; i < 3; i++)
            date[i] = java.sql.Date.valueOf(datestr[i]);

        for (int i = 0; i < 3; i++) {
            Employee emp = new Employee();
            emp.setEmployeeId(employeeId[i]);
            emp.setFirstName(firstName[i]);
            emp.setLastName(lastName[i]);
            emp.setDepartamentId(departamentId[i]);
            emp.setJobTitle(jobTitle[i]);
            emp.setGender(gender[i]);
            emp.setDateOfBirth(date[i]);
            employeeList.add(emp);
        }
        return employeeList;
    }

    private Employee stubEmployee(){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartamentId(departamentId);
        employee.setJobTitle(jobTitle);
        employee.setGender(gender);
        employee.setDateOfBirth(date);
        return employee;
    }
}
