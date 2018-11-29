package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import com.netcracker.cmstore.model.Product;
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
        List list = session.createQuery("select o from Order o left join fetch o.products order by o.id ASC").getResultList();
        for (Object order : list) {
            System.out.println(order.toString());
        }
        return list;
    }

    @Override
    public Order getOrderById(int orderId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Order.class, orderId);
    }

    @Override
    public void removeOrderProduct(int orderId, int productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Order o = session.load(Order.class, orderId);
        if (null != o) {
            for (Product products : o.getProducts()) {
                if (products.getProductId() == productId) {
                    //o.getProducts().remove(products);
                }
            }
            session.save(o);
        }
    }

}
