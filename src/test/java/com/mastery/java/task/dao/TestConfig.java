package com.mastery.java.task.dao;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@TestConfiguration
public class TestConfig {

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:h2:~/test;MODE=PostgreSQL;DATABASE_TO_LOWER=TRUE");
        return ds;
    }

    @Bean
    public EmployeeDaoImpl getEmployeeDao(){
        return new EmployeeDaoImpl(getDataSource());
    }

}
