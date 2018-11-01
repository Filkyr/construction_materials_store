package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Order;

import java.util.List;

public interface OrderDAO {
    void addOrder(Order order);

    void removeOrder(int orderId);

    void updateOrder(Order order);

    List<Order> getOrders();

    Order getOrderById(int orderId);
}
