package com.cesde.dealership.service.impl;

import com.cesde.dealership.model.Customer;
import com.cesde.dealership.repository.CustomerRepository;
import com.cesde.dealership.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(Customer customer, Customer updatedCustomer){
        updatedCustomer.setId(customer.getId());
        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Customer customer){
        customerRepository.delete(customer);
    }
}
