package com.mybank.ReportingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.ReportingService.managers.AccountManager;
import com.mybank.ReportingService.managers.BalanceTransactionManager;
import com.mybank.ReportingService.models.Account;
import com.mybank.ReportingService.models.Transaction;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mybank.ReportingService.constants.AppConstants.QUEUE_ACCOUNT;
import static com.mybank.ReportingService.constants.AppConstants.QUEUE_TRANSACTION;

@Component
public class MessageListener {

    @Autowired
    AccountManager accountManager;

    @Autowired
    BalanceTransactionManager balanceTransactionManager;

    @RabbitListener(queues = QUEUE_ACCOUNT)
    public void accountListener(String message){
        try{
            Account account = new ObjectMapper().readValue(message, Account.class);
            accountManager.createAccount(account);
            System.out.println(account);
        } catch (Exception e){
            System.out.println(e);
            return;
        }
    }
    @RabbitListener(queues = QUEUE_TRANSACTION)
    public void transactionListener(String message){
        try{
            Transaction transaction = new ObjectMapper().readValue(message, Transaction.class);
            balanceTransactionManager.createTransaction(transaction);
            System.out.println(transaction);
        } catch (Exception e){
            System.out.println(e);
            return;
        }
    }
}
