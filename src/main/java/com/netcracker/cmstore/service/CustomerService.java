package com.netcracker.cmstore.service;

import com.netcracker.cmstore.model.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);

    void removeCustomer(int customerId);

    void updateCustomer(Customer customer);

    List<Customer> getCustomers();

    Customer getCustomerById(int customerId);
}
