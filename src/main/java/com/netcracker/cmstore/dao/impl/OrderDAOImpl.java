package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private static final String INSERT_ORDER = "INSERT INTO `order` (customer_id, date) VALUES (?, ?)";
    private static final String SELECT_ORDER = "SELECT `order`.id, `order`.customer_id, `order`.date, order_product.product_id  " +
            "FROM `order` " +
            "INNER JOIN order_product on `order`.id = order_product.order_id " +
            "WHERE `order`.id =?";
    private static final String SELECT_ORDERS = "SELECT `order`.id, `order`.customer_id, `order`.date, op.product_id " +
            "FROM `order` " +
            "LEFT JOIN order_product AS op " +
            "on `order`.id = COALESCE(op.order_id, '') " +
            "ORDER BY `order`.id, op.product_id";
    private static final String UPDATE_ORDER = "UPDATE `order` SET customer_id=?, date=? WHERE `order`.id=?";
    private static final String DELETE_ORDER = "DELETE FROM `order` WHERE `order`.id=?";

    private static final String MISSING_PRODUCTS = "(products in the order is missing)";

    private final DataSource dataSource;

    @Autowired
    public OrderDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addOrder(Order order) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, order.getCustomerId());
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

                statement.setInt(1, order.getCustomerId());
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
                    order.setCustomerId(res.getInt("customer_id"));
                    order.setDate(res.getString("date"));
                    int productId = res.getInt("product_id");
                    if (productId > 0) {
                        order.setProductId(String.valueOf(productId));
                    } else {
                        order.setProductId(MISSING_PRODUCTS);
                    }
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
                        order.setCustomerId(res.getInt("customer_id"));
                        order.setDate(res.getString("date"));
                        order.setProductId(res.getString("product_id"));
                    }
                }
            }
            return order;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
