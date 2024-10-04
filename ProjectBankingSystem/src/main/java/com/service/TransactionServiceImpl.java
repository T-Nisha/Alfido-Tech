package com.service;

import com.dao.IAccountRepository;
import com.dao.ITransactionRepository;
import com.model.Account;
import com.model.Transaction;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private ITransactionRepository transactionRepository;
    @Override
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

       
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);


        Transaction transaction = new Transaction();
        transaction.setFromAccount(fromAccount);
        transaction.setToAccount(toAccount);
        transaction.setAmount(amount);
        transaction.setType("transfer");
        transaction.setTransactionDate(LocalDateTime.now()); 
                transactionRepository.save(transaction);
    }

}
