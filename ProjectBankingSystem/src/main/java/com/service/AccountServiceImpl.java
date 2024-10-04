package com.service;

import com.dao.IAccountRepository;
import com.dao.ICustomerRepository;
import com.model.Account;
import com.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public Account createAccount(Long customerId, Account account) {
       
        Optional<Customer> customer = customerRepository.findById(customerId);

       
        if (customer.isPresent()) {
            account.setCustomer(customer.get());  
            return accountRepository.save(account);  
        } else {
            throw new RuntimeException("Customer with id " + customerId + " not found.");
        }
    }
}
