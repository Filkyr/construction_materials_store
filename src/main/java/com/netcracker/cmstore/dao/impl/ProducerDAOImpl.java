package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.ProducerDAO;
import com.netcracker.cmstore.model.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProducerDAOImpl implements ProducerDAO {

    private static final String INSERT_PRODUCER = "INSERT INTO producer (brand_name, description, logo) VALUES (?, ?, ?)";
    private static final String SELECT_PRODUCER = "SELECT * FROM producer WHERE producer.id =?";
    private static final String SELECT_PRODUCERS = "SELECT * FROM producer";
    private static final String UPDATE_PRODUCER = "UPDATE producer SET brand_name=?, description=?, logo=? WHERE producer.id=?";
    private static final String DELETE_PRODUCER = "DELETE FROM producer WHERE producer.id=?";

    private static final RowMapper<Producer> PRODUCER_ROW_MAPPER = (rs, rowNum) -> {
        Producer producer = new Producer();
        producer.setProducerId(rs.getInt("id"));
        producer.setBrand_name(rs.getString("brand_name"));
        producer.setDescription(rs.getString("description"));
        producer.setLogo(rs.getString("logo"));
        return producer;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProducerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addProducer(Producer producer) {
        jdbcTemplate.update(INSERT_PRODUCER, statement -> {
            statement.setString(1, producer.getBrand_name());
            statement.setString(2, producer.getDescription());
            statement.setString(3, producer.getDescription());
        });
    }

    @Override
    public void removeProducer(int producerId) {
        jdbcTemplate.update(DELETE_PRODUCER, statement -> statement.setInt(1, producerId));
    }

    @Override
    public void updateProducer(Producer producer) {
        jdbcTemplate.update(UPDATE_PRODUCER, statement -> {
            statement.setString(1, producer.getBrand_name());
            statement.setString(2, producer.getDescription());
            statement.setString(3, producer.getLogo());
            statement.setInt(4, producer.getProducerId());
        });
    }

    @Override
    public List<Producer> getProducers() {
        return jdbcTemplate.query(SELECT_PRODUCERS, PRODUCER_ROW_MAPPER);
    }

    @Override
    public Producer getProducerById(int producerId) {
        return jdbcTemplate.queryForObject(SELECT_PRODUCER, new Object[]{producerId}, PRODUCER_ROW_MAPPER);
    }
}
