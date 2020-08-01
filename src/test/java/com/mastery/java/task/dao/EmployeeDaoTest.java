package com.mastery.java.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest()
@ContextConfiguration(classes = TestConfig.class)
@Sql({"/dbmigration/createTable.sql"})
public class EmployeeDaoTest {

    @Autowired
    EmployeeDaoImpl employeeDao;

    @Autowired
    JdbcTemplate template;
/*
    @Test
    public void saveEmployeeTest(){
        Employee employee =  new Employee();

        employeeDao.save(employee);

       Employee savedEmpl =  template.execute();
        assertThat(employee).usingRecursiveComparison().ignoringFields("employeeId").isEqualTo(savedEmpl);
        assertThat(savedEmpl.getEmployeeId()).isGreaterThan(0);
    }
*/
}
