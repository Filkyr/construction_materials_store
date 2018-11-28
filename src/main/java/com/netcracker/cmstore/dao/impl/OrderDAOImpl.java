package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class OrderDAOImpl implements OrderDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(order);
    }

    @Override
    public void removeOrder(int orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        Order p = session.load(Order.class, orderId);
        if (null != p) {
            session.delete(p);
        }
    }

    @Override
    public void updateOrder(Order order) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Order>) session.createQuery("from Order").list();
    }

    @Override
    public Order getOrderById(int orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Order.class, orderId);
    }

}
