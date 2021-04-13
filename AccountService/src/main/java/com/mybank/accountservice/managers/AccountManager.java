package com.mybank.accountservice.managers;

import com.mybank.accountservice.configs.CustomizedMessageException;
import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.BalanceMapper;
import com.mybank.accountservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.mybank.accountservice.constants.AppConstants.*;

@Component
public class AccountManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;

    private void validateAccount(AccountDTO accountDTO){
        List<String> currencyList = Arrays.asList(ALLOWED_CURRENCY);
        for(int i=0;i<accountDTO.getCurrencies().size();i++){
            if(!currencyList.contains(accountDTO.getCurrencies().get(i))){
                throw new CustomizedMessageException("Invalid currency");
            }
        }
    }
    public Account createAccount(AccountDTO accountdto)
    {
        this.validateAccount(accountdto);
        Account account = new Account(UUID.randomUUID().toString(), accountdto.getCustomerId(), accountdto.getCountry(),accountdto.getCurrencies());
        int inserted = accountMapper.insert(account);
        if(inserted == 1) {
            for(int i=0; i<account.getBalances().size();i++){
                balanceMapper.insert(account.getBalances().get(i));
            }
            return account;
        }
        else{
            throw new CustomizedMessageException("Could not write to the database...");
        }
    }
    public Account getAccount(String accountId)
    {
        if(accountId == null || accountId.isEmpty()){
            throw new CustomizedMessageException("accountId is required");
        }
        Account account;
        try{
            account = accountMapper.findById(accountId);
            if(account != null){
                account.setAccountId(accountId);
                account.setBalances(balanceMapper.findById(accountId));
            } else{
                throw new CustomizedMessageException("id: "+ accountId);
            }
        }catch (Exception e){
            throw new CustomizedMessageException("Error occurred while getting account information.");
        }
        return account;
    }
}
