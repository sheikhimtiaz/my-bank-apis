package com.mybank.ReportingService;

import com.mybank.ReportingService.models.Account;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(Account account) {
        System.out.println(account.getAccountId());
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
