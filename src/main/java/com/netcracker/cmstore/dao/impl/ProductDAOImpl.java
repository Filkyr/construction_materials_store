package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.ProductDAO;
import com.netcracker.cmstore.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(product);
    }

    @Override
    public void removeProduct(int productId) {
        Session session = this.sessionFactory.getCurrentSession();
        Product p = session.load(Product.class, productId);
        if (null != p) {
            session.delete(p);
        }
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(product);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getProducts() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Product").list();
    }

    @Override
    public Product getProductById(int productId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Product.class, productId);
    }

}

