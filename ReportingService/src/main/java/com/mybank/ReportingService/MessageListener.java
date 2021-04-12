package com.mybank.ReportingService;

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
    BalanceTransactionManager balanceTransactionManager;

    @RabbitListener(queues = QUEUE_TRANSACTION)
    public void listener(Transaction transaction){
        balanceTransactionManager.createTransaction(transaction);
        System.out.println(transaction);
    }
}
