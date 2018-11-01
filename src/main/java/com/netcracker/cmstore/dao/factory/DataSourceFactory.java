package com.netcracker.cmstore.dao.factory;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/cm_store?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final DataSourceFactory INSTANCE = new DataSourceFactory();

    private final HikariDataSource dataSource;

    public DataSourceFactory() {
        dataSource = new HikariDataSource();
        dataSource.setMaximumPoolSize(2);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
    }

    public static DataSourceFactory getDataSourceFactory() {
        return INSTANCE;
    }

    public synchronized DataSource getDataSource() {
        return dataSource;
    }

    public synchronized void destory() {
        dataSource.close();
    }
}
