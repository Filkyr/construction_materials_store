package com.netcracker.cmstore.configuration;

import com.netcracker.cmstore.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

@Configuration
@PropertySource(value = "classpath:/application.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.netcracker.cmstore.dao", "com.netcracker.cmstore.controller", "com.netcracker.cmstore.service"})
@EnableWebMvc
public class AppConfig {

    private static final String CONNECTION_TIMEOUT = "hibernate.hikari.connectionTimeout";
    private static final String MIN_IDLE = "hibernate.hikari.minimumIdle";
    private static final String MAX_POOL_SIZE = "hibernate.hikari.maximumPoolSize";
    private static final String IDLE_TIMEOUT = "hibernate.hikari.idleTimeout";

    @Bean
    public LocalSessionFactoryBean FactoryBean(Environment env) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setHibernateProperties(createHibernateProperties(env));
        factoryBean.setAnnotatedClasses(Category.class, Customer.class, Order.class, Producer.class, Product.class);

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager TransactionManager(Environment env) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(FactoryBean(env).getObject());
        return transactionManager;
    }

    private Properties createHibernateProperties(Environment env){
        Properties props = new Properties();

        props.put(DRIVER, env.getRequiredProperty("mysql.driver"));
        props.put(URL, env.getRequiredProperty("mysql.url"));
        props.put(USER, env.getRequiredProperty("mysql.user"));
        props.put(PASS, env.getRequiredProperty("mysql.password"));

        props.put(SHOW_SQL, env.getRequiredProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        props.put(GLOBALLY_QUOTED_IDENTIFIERS, env.getRequiredProperty("hibernate.globally_quoted_identifiers"));

        props.put(CONNECTION_TIMEOUT, env.getRequiredProperty(CONNECTION_TIMEOUT));
        props.put(MIN_IDLE, env.getRequiredProperty(MIN_IDLE));
        props.put(MAX_POOL_SIZE, env.getRequiredProperty(MAX_POOL_SIZE));
        props.put(IDLE_TIMEOUT, env.getRequiredProperty(IDLE_TIMEOUT));

        return props;
    }
}



