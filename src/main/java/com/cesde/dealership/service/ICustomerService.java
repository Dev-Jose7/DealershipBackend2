package com.cesde.dealership.service;

import com.cesde.dealership.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getCustomers();
    Optional<Customer> getCustomerById(Integer id);
    Customer updateCustomer(Customer customer, Customer updatedCustomer);
    void deleteCustomer(Customer customer);
}
