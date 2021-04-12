package com.mybank.ReportingService.managers;

import com.mybank.ReportingService.mappers.AccountMapper;
import com.mybank.ReportingService.mappers.BalanceMapper;
import com.mybank.ReportingService.mappers.TransactionMapper;
import com.mybank.ReportingService.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class BalanceTransactionManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    TransactionMapper transactionMapper;

    public Transaction createTransaction(Transaction transaction)
    {
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
        Transaction transaction;
        try{
            transaction = transactionMapper.findById(transactionId);
        }catch (Exception e){
            return null;
        }
        return transaction;
    }
}
