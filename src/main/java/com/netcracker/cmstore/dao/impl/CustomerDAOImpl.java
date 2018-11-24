package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private static final String INSERT_CUSTOMER = "INSERT INTO customer (first_name, last_name, phone_num, address) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER = "SELECT * FROM customer WHERE customer.id =?";
    private static final String SELECT_CUSTOMERS = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET first_name=?, last_name=?, phone_num=?, address=? WHERE customer.id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer.id=?";

    private final DataSource dataSource;

    @Autowired
    public CustomerDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER)) {

                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getPhoneNum());
                statement.setString(4, customer.getAddress());

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeCustomer(int customerId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER)) {
                statement.setInt(1, customerId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER)) {

                statement.setString(1, customer.getFirstName());
                statement.setString(2, customer.getLastName());
                statement.setString(3, customer.getPhoneNum());
                statement.setString(4, customer.getAddress());
                statement.setInt(5, customer.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Customer> getCustomers() {
        try (Connection connection = dataSource.getConnection()) {
            List<Customer> customers = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(SELECT_CUSTOMERS)) {
                while (res.next()) {
                    Customer customer = new Customer();
                    customer.setId(res.getInt("id"));
                    customer.setFirstName(res.getString("first_name"));
                    customer.setLastName(res.getString("last_name"));
                    customer.setPhoneNum(res.getString("phone_num"));
                    customer.setAddress(res.getString("address"));
                    customers.add(customer);
                }
            }
            return customers;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Customer getCustomerById(int customerId) {
        try (Connection connection = dataSource.getConnection()) {
            Customer customer = new Customer();
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER)) {
                statement.setInt(1, customerId);
                try (ResultSet res = statement.executeQuery()) {
                    if (res.next()) {
                        customer.setId(res.getInt("id"));
                        customer.setFirstName(res.getString("first_name"));
                        customer.setLastName(res.getString("last_name"));
                        customer.setPhoneNum(res.getString("phone_num"));
                        customer.setAddress(res.getString("address"));
                    }
                }
            }
            return customer;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}

