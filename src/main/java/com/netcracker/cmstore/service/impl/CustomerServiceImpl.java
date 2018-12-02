package com.netcracker.cmstore.service.impl;

import com.netcracker.cmstore.dao.CustomerDAO;
import com.netcracker.cmstore.model.Customer;
import com.netcracker.cmstore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDao;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional
    @Override
    public void addCustomer(Customer customer) {
        customerDao.addCustomer(customer);
    }

    @Transactional
    @Override
    public void removeCustomer(int customerId) {
        customerDao.removeCustomer(customerId);
    }

    @Transactional
    @Override
    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Customer> getCustomers() {
        return customerDao.getCustomers();
    }

    @Transactional(readOnly = true)
    @Override
    public Customer getCustomerById(int customerId) {
        return customerDao.getCustomerById(customerId);
    }
}
