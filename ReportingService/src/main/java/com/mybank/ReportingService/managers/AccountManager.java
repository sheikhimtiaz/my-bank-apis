package com.mybank.ReportingService.managers;

import com.mybank.ReportingService.mappers.AccountMapper;
import com.mybank.ReportingService.mappers.BalanceMapper;
import com.mybank.ReportingService.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;

    public Account createAccount(Account account)
    {
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
