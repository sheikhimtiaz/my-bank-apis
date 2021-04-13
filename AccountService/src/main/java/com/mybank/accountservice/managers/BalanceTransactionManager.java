package com.mybank.accountservice.managers;

import com.mybank.accountservice.configs.CustomizedMessageException;
import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.BalanceMapper;
import com.mybank.accountservice.mappers.TransactionMapper;
import com.mybank.accountservice.models.Account;
import com.mybank.accountservice.models.Balance;
import com.mybank.accountservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.mybank.accountservice.constants.AppConstants.*;

@Component
public class BalanceTransactionManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    TransactionMapper transactionMapper;

    private void validateTransactionDto (TransactionDTO transactionDTO){
        if(transactionDTO.getAmount() < 0){
            throw new CustomizedMessageException("Invalid amount");
        }
        if(transactionDTO.getDescription() == null || transactionDTO.getDescription().isEmpty()){
            throw new CustomizedMessageException("Description missing");
        }
        if(!transactionDTO.getDirection().equalsIgnoreCase(DIRECTION_IN) && !transactionDTO.getDirection().equalsIgnoreCase(DIRECTION_OUT)){
            throw new CustomizedMessageException("Invalid direction");
        }
        List<String> currencyList = Arrays.asList(ALLOWED_CURRENCY);
        if(!currencyList.contains(transactionDTO.getCurrency())){
            throw new CustomizedMessageException("Invalid currency");
        }
        Account account = accountMapper.findById(transactionDTO.getAccountId());
        if(account == null){
            throw new CustomizedMessageException("Account missing");
        }
    }

    private Transaction updateBalanceAndMakeTransaction(Balance balance, Transaction transaction){
        this.balanceMapper.update(balance);
        transaction.setBalanceAfterTransaction(balance.getAmount());
        int inserted = transactionMapper.insert(transaction);
        if(inserted == 1) {
            return transaction;
        }
        else{
            throw new CustomizedMessageException("Could not write to the database...");
        }
    }

    public Transaction createTransaction(TransactionDTO transactionDTO)
    {
        this.validateTransactionDto(transactionDTO);
        Transaction transaction = new Transaction(UUID.randomUUID().toString(), transactionDTO.getAccountId(),
                transactionDTO.getCurrency(), transactionDTO.getDirection(), transactionDTO.getDescription(), transactionDTO.getAmount());
        List<Balance> balances = balanceMapper.findById(transactionDTO.getAccountId());
        for(int i=0;i<balances.size();i++)
        {
            if(balances.get(i).getCurrency().equalsIgnoreCase(transactionDTO.getCurrency())){
                if(transactionDTO.getDirection().equalsIgnoreCase(DIRECTION_IN)){
                    balances.get(i).setAmount(balances.get(i).getAmount() + transactionDTO.getAmount());
                    return this.updateBalanceAndMakeTransaction(balances.get(i), transaction);
                }
                else if(balances.get(i).getAmount() >= transactionDTO.getAmount()){
                    balances.get(i).setAmount(balances.get(i).getAmount() - transactionDTO.getAmount());
                    return this.updateBalanceAndMakeTransaction(balances.get(i), transaction);

                }
                else{
                    throw new CustomizedMessageException("Insufficient funds");
                }
            }
        }
        throw new CustomizedMessageException("This account is not associated with the given currency.");
    }

    public Transaction getTransaction(String transactionId)
    {
        if(transactionId == null || transactionId.isEmpty()){
            throw new CustomizedMessageException("transactionId is required");
        }
        Transaction transaction;
        try{
            transaction = transactionMapper.findById(transactionId);
            transaction.setTransactionId(transactionId);
        }catch (Exception e){
            throw new CustomizedMessageException("Error occurred while getting transaction information.");
        }
        return transaction;
    }
}
