package com.mybank.accountservice.managers;

import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.BalanceMapper;
import com.mybank.accountservice.mappers.TransactionMapper;
import com.mybank.accountservice.models.Account;
import com.mybank.accountservice.models.Balance;
import com.mybank.accountservice.models.Transaction;
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

    public Transaction createTransaction(TransactionDTO transactionDTO)
    {
        Account account = accountMapper.findById(transactionDTO.getAccountId());
        if(account == null){
            return null;
        }
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), transactionDTO.getAccountId(),
                transactionDTO.getCurrency(), transactionDTO.getDirection(), transactionDTO.getDescription(), transactionDTO.getAmount());
        List<Balance> balances = balanceMapper.findById(transactionDTO.getAccountId());
        for(int i=0;i<balances.size();i++)
        {
            if(balances.get(i).getCurrency().equalsIgnoreCase(transactionDTO.getCurrency())){
                if(transactionDTO.getDirection().equalsIgnoreCase("IN")){
                    balances.get(i).setAmount(balances.get(i).getAmount() + transactionDTO.getAmount());
                    this.balanceMapper.update(balances.get(i));
                    transaction.setBalanceAfterTransaction(balances.get(i).getAmount());
                    int inserted = transactionMapper.insert(transaction);
                    if(inserted == 1) {
                        return transaction;
                    }
                    else{
                        return null;
                    }
                }
                else if(balances.get(i).getAmount() >= transactionDTO.getAmount()){
                    // perform transaction
                    // refactor to this.performTransaction()

                    balances.get(i).setAmount(balances.get(i).getAmount() - transactionDTO.getAmount());
                    this.balanceMapper.update(balances.get(i));
                    transaction.setBalanceAfterTransaction(balances.get(i).getAmount());
                    // insert transaction to the table
                    // refactor to this.insertTransaction()

                    int inserted = transactionMapper.insert(transaction);
                    if(inserted == 1) {
                        return transaction;
                    }
                    else{
                        return null;
                    }
                }
                else{
                    //throw new Exception("Insufficient funds");
                }
            }
        }
        // invalid currency
        return null;
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
