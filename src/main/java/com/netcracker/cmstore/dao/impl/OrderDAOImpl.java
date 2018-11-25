package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs, rowNum) -> {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setDate(rs.getString("date"));
        order.setProductId(rs.getString("product_id"));

        return order;
    };

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addOrder(Order order) {
        jdbcTemplate.update(INSERT_ORDER, statement -> {
            statement.setInt(1, order.getCustomerId());
            statement.setString(2, order.getDate());
        });
    }

    @Override
    public void removeOrder(int orderId) {
        jdbcTemplate.update(DELETE_ORDER, statement -> statement.setInt(1, orderId));
    }

    @Override
    public void updateOrder(Order order) {
        jdbcTemplate.update(UPDATE_ORDER, statement -> {
            statement.setInt(1, order.getCustomerId());
            statement.setString(2, order.getDate());
            statement.setInt(3, order.getId());
        });

    }

    @Override
    public List<Order> getOrders() {
        return jdbcTemplate.query(SELECT_ORDERS, ORDER_ROW_MAPPER);
    }

    @Override
    public Order getOrderById(int orderId) {
        return jdbcTemplate.queryForObject(SELECT_ORDER, new Object[]{orderId}, ORDER_ROW_MAPPER);
    }

}
