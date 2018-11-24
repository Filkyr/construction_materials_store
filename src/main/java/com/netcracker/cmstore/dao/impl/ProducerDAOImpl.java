package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProducerDAOImpl implements ProducerDAO {

    private static final String INSERT_PRODUCER = "INSERT INTO producer (brand_name, description, logo) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCER = "SELECT * FROM producer WHERE producer.id =?";
    private static final String SELECT_PRODUCERS = "SELECT * FROM producer";
    private static final String UPDATE_PRODUCER = "UPDATE producer SET brand_name=?, description=?, logo=? WHERE producer.id=?";
    private static final String DELETE_PRODUCER = "DELETE FROM producer WHERE producer.id=?";

    private final DataSource dataSource;

    @Autowired
    public ProducerDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addProducer(Producer producer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_PRODUCER)) {

                statement.setString(1, producer.getBrand_name());
                statement.setString(2, producer.getDescription());
                statement.setString(3, producer.getDescription());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeProducer(int producerId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCER)) {
                statement.setInt(1, producerId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateProducer(Producer producer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCER)) {
                statement.setString(1, producer.getBrand_name());
                statement.setString(2, producer.getDescription());
                statement.setString(3, producer.getLogo());
                statement.setInt(4, producer.getProducerId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Producer> getProducers() {
        try (Connection connection = dataSource.getConnection()) {
            List<Producer> producers = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(SELECT_PRODUCERS)) {
                while (res.next()) {
                    Producer producer = new Producer();
                    producer.setProducerId(res.getInt("id"));
                    producer.setBrand_name(res.getString("brand_name"));
                    producer.setDescription(res.getString("description"));
                    producer.setLogo(res.getString("logo"));
                    producers.add(producer);
                }
            }
            return producers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Producer getProducerById(int producerId) {
        try (Connection connection = dataSource.getConnection()) {
            Producer producer = new Producer();
            try (PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCER)) {
                statement.setInt(1, producerId);
                try (ResultSet res = statement.executeQuery()) {
                    if (res.next()) {
                        producer.setProducerId(res.getInt("id"));
                        producer.setBrand_name(res.getString("brand_name"));
                        producer.setDescription(res.getString("description"));
                        producer.setLogo(res.getString("logo"));
                    }
                }
            }
            return producer;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
