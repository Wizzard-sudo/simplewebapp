package com.mastery.java.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Value("${db.driver.class.name.test}")
    private String DriverClassName;

    @Value("${db.url.test}")
    private String Url;

    @Value("${db.Username.test}")
    private String Username;

    @Value("${db.Password.test}")
    private String Password;

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DriverClassName);
        ds.setUrl(Url);
        ds.setUsername(Username);
        ds.setPassword(Password);
        return ds;
    }

//    @Bean
//    public EmployeeDaoImpl getEmployeeDao(@Autowired DataSource dataSource){
//        return new EmployeeDaoImpl(dataSource);
//    }

}
