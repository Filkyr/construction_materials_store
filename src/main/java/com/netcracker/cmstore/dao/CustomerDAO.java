package com.netcracker.cmstore.dao;

import com.netcracker.cmstore.model.Customer;

import java.util.List;

public interface CustomerDAO {
    void removeCustomer(int customerId);

    void insertOrUpdateCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(int customerId);
}
