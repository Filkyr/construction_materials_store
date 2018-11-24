package com.netcracker.cmstore.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@PropertySource(value = "classpath:/application.properties")
@ComponentScan(basePackages = {"com.netcracker.cmstore.dao", "com.netcracker.cmstore.controller"})
@EnableWebMvc
public class AppConfig {

    @Bean
    public DataSource dataSource(Environment env) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty("driverClass"));
        dataSource.setJdbcUrl(env.getRequiredProperty("url"));
        dataSource.setUsername(env.getRequiredProperty("user"));
        dataSource.setPassword(env.getRequiredProperty("password"));
        dataSource.setMaximumPoolSize(env.getRequiredProperty("maximumPoolSize", int.class));
        return dataSource;
    }
}
