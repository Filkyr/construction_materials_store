package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderProductDAO;
import com.netcracker.cmstore.model.OrderProduct;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class OrderProductDAOImpl implements OrderProductDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public OrderProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(orderProduct);
    }

    @Override
    public void removeOrderProduct(int orderId, int orderProductId) {
//        Session session = this.sessionFactory.getCurrentSession();
//        OrderProduct p = session.load(OrderProduct.class, orderProductId);
//        if (null != p) {
//            session.delete(p);
//        }
    }

}