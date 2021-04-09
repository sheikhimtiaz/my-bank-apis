package com.mybank.accountservice.controllers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mybank.accountservice.constants.AppConstants.SC_OK;
import static com.mybank.accountservice.constants.AppConstants.SC_SERVER_ERROR;

@RestController
@RequestMapping("/api")
public class AccountController extends BaseController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    AccountMapper accountMapper;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody Account data) {
        data.setAccountId(UUID.randomUUID().toString());
        int inserted = accountMapper.insert(data);
        System.out.println("A call received : ");
        System.out.println(inserted);
        if(inserted == 1) {
            return getResponse("success", "insert successful", data, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", data, SC_SERVER_ERROR);
        }
    }

    @GetMapping("/account")
    public Account getAccount(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Account();
    }

}
