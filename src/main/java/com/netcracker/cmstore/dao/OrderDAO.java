package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Order;

import java.util.List;

public interface OrderDAO {

    void addOrder(Order order);

    void addOrderProduct(int orderId, int productId);

    void removeOrder(int orderId);

    void removeOrderProduct(int orderId, int productId);

    List<Order> getOrders();

}
