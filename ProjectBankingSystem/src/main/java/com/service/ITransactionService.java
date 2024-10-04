package com.service;

public interface ITransactionService {
    void transfer(Long fromAccountId, Long toAccountId, double amount);
}

