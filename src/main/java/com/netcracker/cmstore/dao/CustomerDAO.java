package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    private Connection connection;

    private static final String INSERT_CUSTOMER = "INSERT INTO customer (first_name, last_name, phone_num, address) VALUES (?, ?, ?, ?)";
    private static final String SELECT_CUSTOMER = "SELECT * FROM customer WHERE customer.id =?";
    private static final String SELECT_CUSTOMERS = "SELECT * FROM customer";
    private static final String UPDATE_CUSTOMER = "UPDATE customer SET first_name=?, last_name=?, phone_num=?, address=? WHERE customer.id=?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customer WHERE customer.id=?";

    public CustomerDAO() {
        ConnectionClass con = new ConnectionClass();
        try {
            connection = con.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCustomer(Customer customer) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_CUSTOMER);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNum());
            statement.setString(4, customer.getAddress());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCustomer(int customerId) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);
            statement.setInt(1, customerId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer customer) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER);

            statement.setString(1, customer.getFirstName());
            statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getPhoneNum());
            statement.setString(4, customer.getAddress());
            statement.setInt(5, customer.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(SELECT_CUSTOMERS);
        while (res.next()) {
            Customer customer = new Customer();
            customer.setId(res.getInt("id"));
            customer.setFirstName(res.getString("first_name"));
            customer.setLastName(res.getString("last_name"));
            customer.setPhoneNum(res.getString("phone_num"));
            customer.setAddress(res.getString("address"));
            customers.add(customer);
        }
        return customers;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        Customer customer = new Customer();
        PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER);
        statement.setInt(1, customerId);
        ResultSet res = statement.executeQuery();
        if (res.next()) {
            customer.setId(res.getInt("id"));
            customer.setFirstName(res.getString("first_name"));
            customer.setLastName(res.getString("last_name"));
            customer.setPhoneNum(res.getString("phone_num"));
            customer.setAddress(res.getString("address"));
        }
        return customer;
    }

}

