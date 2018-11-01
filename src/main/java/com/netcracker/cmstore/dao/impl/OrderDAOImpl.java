package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.Order;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final String INSERT_ORDER = "INSERT INTO order (customer_id, date) VALUES (?, ?)";
    private static final String SELECT_ORDER = "SELECT * FROM order WHERE order.id =?";
    private static final String SELECT_ORDERS = "SELECT * FROM order";
    private static final String UPDATE_ORDER = "UPDATE order SET customer_id=?, date=? WHERE order.id=?";
    private static final String DELETE_ORDER = "DELETE FROM order WHERE order.id=?";

    private final DataSource dataSource;

    public OrderDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addOrder(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER)) {

                statement.setString(1, order.getCustomerId());
                statement.setString(2, order.getDate());

                statement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeOrder(int orderId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_ORDER)) {
                statement.setInt(1, orderId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER)) {

                statement.setString(1, order.getCustomerId());
                statement.setString(2, order.getDate());
                statement.setInt(3, order.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> getOrders() {
        try (Connection connection = dataSource.getConnection()) {
            List<Order> orders = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(SELECT_ORDERS)) {
                while (res.next()) {
                    Order order = new Order();
                    order.setId(res.getInt("id"));
                    order.setCustomerId(res.getString("customer_id"));
                    order.setDate(res.getString("date"));
                    orders.add(order);
                }
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Order getOrderById(int orderId) {
        try (Connection connection = dataSource.getConnection()) {
            Order order = new Order();
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER)) {
                statement.setInt(1, orderId);
                try (ResultSet res = statement.executeQuery()) {
                    if (res.next()) {
                        order.setId(res.getInt("id"));
                        order.setCustomerId(res.getString("first_name"));
                        order.setDate(res.getString("date"));
                    }
                }
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
