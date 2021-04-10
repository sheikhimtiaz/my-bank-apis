package com.mybank.accountservice.managers;

import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.TransactionMapper;
import com.mybank.accountservice.models.Account;
import com.mybank.accountservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class TransactionManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    TransactionMapper transactionMapper;

    public Transaction createTransaction(TransactionDTO transactionDTO)
    {
        Account account = accountMapper.findById(transactionDTO.getAccountId());
        if(){

        }
        Transaction transaction = new Transaction(transactionDTO);
        transaction.setTransactionId(UUID.randomUUID().toString());
        int inserted = transactionMapper.insert(transaction);
        if(inserted == 1) {
            return transaction;
        }
        else{
            return null;
        }
    }


    public Transaction getTransaction(String transactionId)
    {
        Transaction transaction = transactionMapper.findById(transactionId);;
        if(transaction != null) {
            return transaction;
        }
        else{
            return null;
        }
    }
}
