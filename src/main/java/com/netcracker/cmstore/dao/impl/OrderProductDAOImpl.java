package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.model.OrderProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderProductDAOImpl implements OrderProductDAO {

    private static final String INSERT_OrderProduct = "INSERT INTO order_product (order_product.order_id, order_product.product_id) VALUES (?, ?)";

    private static final String DELETE_OrderProduct = "DELETE FROM order_product WHERE order_product.order_id=? AND order_product.product_id=?";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderProductDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        jdbcTemplate.update(INSERT_OrderProduct, statement -> {

            statement.setInt(1, orderProduct.getOrderId());
            statement.setInt(2, orderProduct.getProductId());

        });
    }

    @Override
    public void removeOrderProduct(int orderId, int productId) {
        jdbcTemplate.update(DELETE_OrderProduct, statement -> {
            statement.setInt(1, orderId);
            statement.setInt(2, productId);

        });
    }
}