package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.dao.exception.DaoException;
import com.netcracker.cmstore.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {

    private static final String INSERT_OrderProduct = "INSERT INTO order_product (order_product.order_id, order_product.product_id) VALUES (?, ?)";

    private static final String DELETE_OrderProduct = "DELETE FROM order_product WHERE order_product.order_id=? AND order_product.product_id=?";

    private final DataSource dataSource;

    @Autowired
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
}