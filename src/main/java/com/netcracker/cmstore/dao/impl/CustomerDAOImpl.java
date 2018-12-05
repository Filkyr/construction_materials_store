package com.netcracker.cmstore.dao.impl;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
    public void insertOrUpdateCustomer(Customer customer) {
        Session session = this.sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
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
        return session.get(Customer.class, customerId);
    }

}

