package com.mastery.java.task.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@PropertySource("application.properties")
@Configuration
public class AppConfiguration {

    @Value("${db.driver.class.name}")
    private String DriverClassName;

    @Value("${db.url}")
    private String Url;

    @Value("${db.Username}")
    private String Username;

    @Value("${db.Password}")
    private String Password;

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(DriverClassName);
        ds.setUrl(Url);
        ds.setUsername(Username);
        ds.setPassword(Password);
        return ds;
    }

}
