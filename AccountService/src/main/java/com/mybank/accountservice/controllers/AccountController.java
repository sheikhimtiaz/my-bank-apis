package com.mybank.accountservice.controllers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.managers.AccountManager;
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

    @Autowired
    AccountManager accountManager;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO data) {
        Account account = accountManager.createAccount(data);
        if(account != null) {
            // publish rabbitmq event => account
            return getResponse("success", "insert successful", account, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", null, SC_SERVER_ERROR);
        }
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(@RequestParam(value = "accountId", defaultValue = "World") String accountId) {
        Account account = accountManager.getAccount(accountId);
        if(account != null) {
            // publish rabbitmq event => account
            return getResponse("success", "query successful", account, SC_OK);
        }
        else{
            return getResponse("failed", "query failed", null, SC_SERVER_ERROR);
        }
    }

}
