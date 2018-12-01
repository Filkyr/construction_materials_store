package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Order;

import java.util.List;

public interface OrderService {
    void addOrder(Order order);

    void addOrderProduct(int orderId, int productId);

    void removeOrder(int orderId);

    void removeOrderProduct(int orderId, int productId);

    List<Order> getOrders();
}
