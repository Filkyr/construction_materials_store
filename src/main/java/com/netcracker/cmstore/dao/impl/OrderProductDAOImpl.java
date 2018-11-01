package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.OrderProduct;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class OrderProductDAOImpl implements OrderProductDAO {

    private static final String INSERT_OrderProduct = "INSERT INTO order_product (customer_id, date) VALUES (?, ?)";
    private static final String SELECT_OrderProduct = "SELECT * FROM orderProduct WHERE orderProduct.id =?";
    private static final String SELECT_OrderProductS = "SELECT * FROM order_product";
    private static final String UPDATE_OrderProduct = "UPDATE order_product SET customer_id=?, date=? WHERE orderProduct.id=?";
    private static final String DELETE_OrderProduct = "DELETE FROM order_product WHERE order_product.order_id=? AND order_product.product_id=?";

    private final DataSource dataSource;

    public OrderProductDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_OrderProduct)) {

                statement.setInt(1, orderProduct.getOrderId());
                statement.setInt(2, orderProduct.getProductId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void removeOrderProduct(int orderId, int productId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_OrderProduct)) {
                statement.setInt(1, orderId);
                statement.setInt(2, productId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void updateOrderProduct(OrderProduct orderProduct) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_OrderProduct)) {

                statement.setInt(3, orderProduct.getOrderId());
                statement.setInt(3, orderProduct.getProductId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ArrayList<OrderProduct> getOrderProducts() {
        try (Connection connection = dataSource.getConnection()) {
            ArrayList<OrderProduct> orderProducts = new ArrayList<>();
            try (Statement stmt = connection.createStatement();
                 ResultSet res = stmt.executeQuery(SELECT_OrderProductS)) {
                while (res.next()) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setOrderId(res.getInt("order_id"));
                    orderProduct.setProductId(res.getInt("product_id"));
                    orderProducts.add(orderProduct);
                }
            }
            return orderProducts;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public OrderProduct getOrderProductById(int orderProductId) {
        try (Connection connection = dataSource.getConnection()) {
            OrderProduct orderProduct = new OrderProduct();
            try(PreparedStatement statement = connection.prepareStatement(SELECT_OrderProduct)) {
                statement.setInt(1, orderProductId);
                try (ResultSet res = statement.executeQuery()) {
                    if (res.next()) {
                        orderProduct.setOrderId(res.getInt("id"));
                        orderProduct.setProductId(res.getInt("id"));
                    }
                }
            }
            return orderProduct;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}