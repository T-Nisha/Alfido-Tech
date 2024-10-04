package com.controller;

import com.model.Account;
import com.model.AccountRequest;
import com.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        Account account = new Account();
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setBalance(accountRequest.getBalance());

    
        Account createdAccount = accountService.createAccount(accountRequest.getCustomerId(), account);

       
        return ResponseEntity.ok(createdAccount);
    }
}
