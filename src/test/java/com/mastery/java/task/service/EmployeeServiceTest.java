package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.mastery.java.task.dto.Gender.FEMALE;
import static com.mastery.java.task.dto.Gender.MALE;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeService employeeService;

    @Mock
    EmployeeDao employeeDao;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTest(){
        List<Employee> employeeList = new ArrayList<Employee>();

        int[] employeeId = new int[]{1, 2, 3};
        String[] firstName = new String[]{"Andy", "Joe Lo", "Chelsea"};
        String[] lastName = new String[]{"Samberg", "Truglio", "Peretti"};
        int[] departamentId = new int[]{1, 2, 3};
        String[] jobTitle = new String[]{"Java Developer", "HR", "Tester"};
        Gender[] gender = new Gender[]{MALE, MALE, FEMALE};
        Date[] date = new Date[3];
        date[0] = new Date(1989, Calendar.MARCH, 2);
        date[1] = new Date(1993, Calendar.JUNE, 5);
        date[2] = new Date(1995, Calendar.AUGUST, 7);

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
        when(employeeDao.getAll()).thenReturn(employeeList);

        List<Employee> empList = employeeService.getAll();

        assertEquals( 3, empList.size());
        then(employeeDao).should().getAll();

        System.out.println("getAllTest is successful");
    }

    @Test
    public void getByIdTest(){
        int employeeId = 1;
        String firstName = "Andy";
        String lastName = "Samberg";
        int departamentId = 1;
        String jobTitle = "Java Developer";
        Gender gender = MALE;
        Date date = new Date(1989, Calendar.MARCH, 2);

        Employee emp = new Employee();
        emp.setEmployeeId(employeeId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setDepartamentId(departamentId);
        emp.setJobTitle(jobTitle);
        emp.setGender(gender);
        emp.setDateOfBirth(date);

        when(employeeDao.getById(employeeId)).thenReturn(emp);

        Employee employee = employeeService.getById(employeeId);

        assertEquals( employeeId, employee.getEmployeeId());
        assertEquals( firstName, employee.getFirstName());
        assertEquals( lastName, employee.getLastName());
        assertEquals( departamentId, employee.getDepartamentId());
        assertEquals( gender, employee.getGender());
        assertEquals( date, employee.getDateOfBirth());

        System.out.println("getByIdTest is successful");
    }

    @Test
    public void saveTest(){
        int employeeId = 1;
        String firstName = "Andy";
        String lastName = "Samberg";
        int departamentId = 1;
        String jobTitle = "Java Developer";
        Gender gender = MALE;
        Date date = new Date(1989, Calendar.MARCH, 2);

        Employee emp = new Employee();
        emp.setEmployeeId(employeeId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setDepartamentId(departamentId);
        emp.setJobTitle(jobTitle);
        emp.setGender(gender);
        emp.setDateOfBirth(date);

        employeeService.save(emp);

        then(employeeDao).should().save(emp);

        System.out.println("saveTest is successful");
    }

    @Test
    public void deleteByIdTest(){
        int id = 1;

        employeeService.deleteById(id);

        then(employeeDao).should().deleteById(id);

        System.out.println("deleteByIdTest is successful");
    }

    @Test
    public void updateTest(){
        int employeeId = 1;
        String firstName = "Chelsea";
        String lastName = "Peretti";
        int departamentId = 3;
        String jobTitle = "Tester";
        Gender gender = FEMALE;
        Date date = new Date(1995, Calendar.AUGUST,7);

        Employee emp = new Employee();
        emp.setEmployeeId(employeeId);
        emp.setFirstName(firstName);
        emp.setLastName(lastName);
        emp.setDepartamentId(departamentId);
        emp.setJobTitle(jobTitle);
        emp.setGender(gender);
        emp.setDateOfBirth(date);

        employeeService.update(emp);

        then(employeeDao).should().update(emp);

        System.out.println("updateTest is successful");
    }
}
