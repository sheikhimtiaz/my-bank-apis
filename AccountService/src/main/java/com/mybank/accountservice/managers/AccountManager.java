package com.mybank.accountservice.managers;

import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.mappers.BalanceMapper;
import com.mybank.accountservice.models.Account;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.mybank.accountservice.constants.AppConstants.SC_OK;
import static com.mybank.accountservice.constants.AppConstants.SC_SERVER_ERROR;

@Component
public class AccountManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;

    public Account createAccount(AccountDTO accountdto)
    {
        Account account = new Account(UUID.randomUUID().toString(), accountdto.getCustomerId(), accountdto.getCountry(),accountdto.getCurrencies());
        int inserted = accountMapper.insert(account);
        if(inserted == 1) {
            for(int i=0; i<account.getBalances().size();i++){
                balanceMapper.insert(account.getBalances().get(i));
            }
            return account;
        }
        else{
            return null;
        }
    }
    public Account getAccount(String accountId)
    {
        Account account;
        try{
            account = accountMapper.findById(accountId);
            if(account != null){
                account.setAccountId(accountId);
                account.setBalances(balanceMapper.findById(accountId));
            }
        }catch (Exception e){
            return null;
        }
        return account;
    }
}
