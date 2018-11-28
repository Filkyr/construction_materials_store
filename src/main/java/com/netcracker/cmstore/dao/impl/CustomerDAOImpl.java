package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(customer);
    }

    @Override
    public void removeCustomer(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        Customer p = session.load(Customer.class, customerId);
        if (null != p) {
            session.delete(p);
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(customer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Customer> getCustomers() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Customer").list();
    }

    @Override
    public Customer getCustomerById(int customerId) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.load(Customer.class, customerId);
    }

}

