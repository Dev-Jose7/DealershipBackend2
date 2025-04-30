package com.cesde.dealership.controller;

import com.cesde.dealership.model.Customer;
import com.cesde.dealership.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> customerList = customerService.getCustomers();

        if(customerList.isEmpty()){
            return new ResponseEntity<>(customerList, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        Optional<Customer> customer = customerService.getCustomerById(id);

        return customer.map(customerFound -> new ResponseEntity<>(customerFound, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Customer> editCustomer (@PathVariable Integer id, @Valid @RequestBody Customer newData){
        Optional<Customer> customerFound = customerService.getCustomerById(id);

        if(customerFound.isPresent()){
            Customer updatedCustomer = customerService.updateCustomer(customerFound.get(), newData);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer (@PathVariable Integer id){
        Optional<Customer> customer = customerService.getCustomerById(id);

        if(customer.isPresent()){
            customerService.deleteCustomer(customer.get());
            return new ResponseEntity<>("Cliente eliminado con exito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cliente no encontrado o no valido", HttpStatus.NOT_FOUND);
        }
    }
}
