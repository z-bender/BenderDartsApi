package ru.bender.darts.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource(value = "sqlite.properties")
@ComponentScan(basePackages = "ru.bender.darts.api.dao")
public class DBConfig {

    @Value("${sqlite.dburl}")
    private String dbUrl;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.sqlite.JDBC");
        driverManagerDataSource.setUrl("jdbc:sqlite:darts.db");
        driverManagerDataSource.setUsername("");
        driverManagerDataSource.setPassword("");
        return driverManagerDataSource;
    }
}
