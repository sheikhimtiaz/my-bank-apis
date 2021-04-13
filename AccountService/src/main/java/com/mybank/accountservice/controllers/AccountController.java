package com.mybank.accountservice.controllers;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.managers.AccountManager;
import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.models.Account;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mybank.accountservice.constants.AppConstants.*;

@RestController
@RequestMapping("/api")
public class AccountController extends BaseController {

    @Autowired
    AccountManager accountManager;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@RequestBody AccountDTO data) {
        Account account = accountManager.createAccount(data);
        if(account != null) {
            try {
                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_ACCOUNT, objectMapper.writeValueAsString(account));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return getResponse("success", "insert successful", account, SC_OK);
        }
        else{
            return getResponse("error", "insert failed", null, SC_NOT_ACCEPTABLE);
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
            return getResponse("error", "query failed", null, SC_BAD_REQUEST);
        }
    }

}
