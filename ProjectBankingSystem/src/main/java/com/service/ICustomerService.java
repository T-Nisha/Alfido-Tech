package com.service;

import com.model.Customer;

import java.util.List;

public interface ICustomerService {

Customer createCustomer(Customer customer);
Customer getCustomerById(Long id);
List<Customer> getAllCustomers();

}
