package com.netcracker.cmstore.dao.impl;


import com.netcracker.cmstore.dao.OrderDAO;
import com.netcracker.cmstore.model.Order;
import com.netcracker.cmstore.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
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
    public void addOrderProduct(int orderId, int productId) {
        Session session = this.sessionFactory.getCurrentSession();

        Order order = session.load(Order.class, orderId);
        Product product = session.load(Product.class, productId);

        List<Product> productList = order.getProducts();
        productList.add(product);

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> getOrders() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Order o left join fetch o.products order by o.id ASC").getResultList();
    }

    @Override
    public void removeOrderProduct(int orderId, int productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Order o = session.load(Order.class, orderId);
        List<Product> productList = o.getProducts();
        if (o != null) {
            productList.removeIf(product -> product.getProductId() == productId);
            session.save(o);
        }
    }

}
