package com.netcracker.cmstore.service.impl;

import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import com.netcracker.cmstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDao;

    @Transactional
    @Override
    public void addOrder(Order order) {
        orderDao.addOrder(order);
    }

    @Transactional
    @Override
    public void addOrderProduct(int orderId, int productId) {
        orderDao.addOrderProduct(orderId, productId);
    }

    @Transactional
    @Override
    public void removeOrder(int orderId) {
        orderDao.removeOrder(orderId);
    }

    @Transactional
    @Override
    public void removeOrderProduct(int orderId, int productId) {
        orderDao.removeOrderProduct(orderId, productId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }
}
