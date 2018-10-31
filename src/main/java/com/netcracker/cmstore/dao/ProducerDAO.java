package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Producer;

import java.sql.*;
import java.util.ArrayList;

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

    public void addProducer(Producer producer) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCER);

            statement.setString(1, producer.getBrand_name());
            statement.setString(2, producer.getDescription());
            statement.setString(3, producer.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeProducer(int producerId) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCER);
            statement.setInt(1, producerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProducer(Producer producer) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCER);
            statement.setString(1, producer.getBrand_name());
            statement.setString(2, producer.getDescription());
            statement.setString(3, producer.getLogo());
            statement.setInt(4, producer.getProducerId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Producer> getProducers() throws SQLException {
        ArrayList<Producer> producers = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(SELECT_PRODUCERS);
        while (res.next()) {
            Producer producer = new Producer();
            producer.setProducerId(res.getInt("id"));
            producer.setBrand_name(res.getString("brand_name"));
            producer.setDescription(res.getString("description"));
            producer.setLogo(res.getString("logo"));
            producers.add(producer);
        }
//        producers.forEach((cat) -> System.out.println(cat.getProducerId() + " - " + cat.getTitle() + " - " + cat.getDescription()));
        return producers;
    }

    public Producer getProducerById(int producerId) throws SQLException {
        Producer producer = new Producer();
        PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCER);
        statement.setInt(1, producerId);
//        String query = "select * from producer where producer.id = " + producerId + " ";
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            producer.setProducerId(res.getInt("id"));
            producer.setBrand_name(res.getString("brand_name"));
            producer.setDescription(res.getString("description"));
            producer.setLogo(res.getString("logo"));
        }
        return producer;
    }

}
