package com.service;

import com.model.Account;

import java.util.List;

public interface IAccountService {

	 Account createAccount(Long customerId, Account account);
    
}

