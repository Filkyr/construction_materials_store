package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (first_name, last_name, phone_num, address) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER = "SELECT * FROM customer WHERE customer.id =?";
    private static final String SELECT_CUSTOMERS = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET first_name=?, last_name=?, phone_num=?, address=? WHERE customer.id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer.id=?";

    private static final RowMapper<Customer> CUSTOMER_ROW_MAPPER = (rs, rowNum) -> {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setPhoneNum(rs.getString("phone_num"));
        customer.setAddress(rs.getString("address"));
        return customer;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER, statement -> {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNum());
            statement.setString(4, customer.getAddress());
        });
    }

    @Override
    public void removeCustomer(int customerId) {
        jdbcTemplate.update(DELETE_CUSTOMER, statement -> statement.setInt(1, customerId));
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER, statement -> {
            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNum());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getId());
        });
    }

    @Override
    public List<Customer> getCustomers() {
        return jdbcTemplate.query(SELECT_CUSTOMERS, CUSTOMER_ROW_MAPPER);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        return jdbcTemplate.queryForObject(SELECT_CUSTOMER, new Object[]{customerId}, CUSTOMER_ROW_MAPPER);
    }
}

