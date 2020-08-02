package com.mastery.java.task.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.h2.Driver");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setUrl("jdbc:h2:~/test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE");
        return ds;
    }

    @Bean
    public EmployeeDaoImpl getEmployeeDao(@Autowired DataSource dataSource){
        return new EmployeeDaoImpl(dataSource);
    }

}
