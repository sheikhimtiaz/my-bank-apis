package com.mybank.ReportingService.managers;

import com.mybank.ReportingService.mappers.AccountMapper;
import com.mybank.ReportingService.mappers.BalanceMapper;
import com.mybank.ReportingService.mappers.TransactionMapper;
import com.mybank.ReportingService.models.Account;
import com.mybank.ReportingService.models.Balance;
import com.mybank.ReportingService.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.mybank.ReportingService.constants.AppConstants.DIRECTION_IN;

@Component
public class BalanceTransactionManager {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    BalanceMapper balanceMapper;
    @Autowired
    TransactionMapper transactionMapper;

    private void updateBalanceForAccount(Transaction transaction){
        List<Balance> balances = balanceMapper.findById(transaction.getAccountId());
        for(int i=0;i<balances.size();i++){
            if(balances.get(i).getCurrency().equalsIgnoreCase(transaction.getCurrency())){
                if(transaction.getDirection().equalsIgnoreCase(DIRECTION_IN)){
                    balances.get(i).setAmount(balances.get(i).getAmount() + transaction.getAmount());
                    balanceMapper.update(balances.get(i));
                }
                else {
                    balances.get(i).setAmount(balances.get(i).getAmount() - transaction.getAmount());
                    balanceMapper.update(balances.get(i));

                }
            }
        }

    }

    public Transaction createTransaction(Transaction transaction)
    {
        this.updateBalanceForAccount(transaction);
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
