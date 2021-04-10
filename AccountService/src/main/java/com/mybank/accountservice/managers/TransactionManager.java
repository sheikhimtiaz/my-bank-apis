package com.mybank.accountservice.managers;

import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.BalanceMapper;
import com.mybank.accountservice.mappers.TransactionMapper;
import com.mybank.accountservice.models.Account;
import com.mybank.accountservice.models.Balance;
import com.mybank.accountservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TransactionManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    TransactionMapper transactionMapper;

    public Transaction createTransaction(TransactionDTO transactionDTO)
    {
        Account account = accountMapper.findById(transactionDTO.getAccountId());
        Transaction transaction = new Transaction(transactionDTO);
        transaction.setTransactionId(UUID.randomUUID().toString());
        if(account != null){
            account.setAccountId(transactionDTO.getAccountId());
            account.setBalances(balanceMapper.findById(transactionDTO.getAccountId()));
        }
        List<Balance> balances = account.getBalances();
        for(int i=0;i<balances.size();i++)
        {
            if(balances.get(i).getCurrency() == transactionDTO.getCurrency()){
                if(transactionDTO.getDirection().toString() == "IN"){
                    balances.get(i).setAmount(balances.get(i).getAmount() + transactionDTO.getAmount());
                    this.balanceMapper.update(balances.get(i));
                    int inserted = transactionMapper.insert(transaction);
                    if(inserted == 1) {
                        return transaction;
                    }
                    else{
                        return null;
                    }
                }
                else if(balances.get(i).getAmount() <= transactionDTO.getAmount()){
                    // perform transaction
                    // refactor to this.performTransaction()

                    balances.get(i).setAmount(balances.get(i).getAmount() - transactionDTO.getAmount());
                    this.balanceMapper.update(balances.get(i));
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
        return transaction;
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
