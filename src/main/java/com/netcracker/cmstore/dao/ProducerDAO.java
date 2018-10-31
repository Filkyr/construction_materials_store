package com.netcracker.cmstore.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class ProducerDAO {
    private Connection connection;

    private static final String INSERT_PRODUCER = "INSERT INTO producer (brand_name, description, logo) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCER = "SELECT * FROM producer WHERE producer.id =?";
    private static final String SELECT_PRODUCERS = "SELECT * FROM producer";
    private static final String UPDATE_PRODUCER = "UPDATE producer SET brand_name=?, description=?, logo=? WHERE producer.id=?";
    private static final String DELETE_PRODUCER = "DELETE FROM producer WHERE producer.id=?";

    public ProducerDAO() {
        ConnectionClass con = new ConnectionClass();
        try {
            connection = con.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    will be completed later...
}
