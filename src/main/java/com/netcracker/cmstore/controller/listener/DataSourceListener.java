package com.netcracker.cmstore.controller.listener;

import com.netcracker.cmstore.dao.factory.DataSourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class DataSourceListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(DataSourceListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataSourceFactory.getDataSourceFactory().getDataSource();
        logger.info("DataSource was initialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DataSourceFactory.getDataSourceFactory().destory();
        logger.info("DataSource was closed!");
    }
}
