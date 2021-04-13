package com.mybank.accountservice.controllers;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.managers.AccountManager;
import com.mybank.accountservice.models.Account;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

import static com.mybank.accountservice.constants.AppConstants.*;

@RestController
@RequestMapping("/api")
public class AccountController extends BaseController {

    @Autowired
    AccountManager accountManager;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    private Validator validator;

    @PostMapping("/account")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO data) {
        Account account = accountManager.createAccount(data);
        if(account != null) {
            try {
                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_ACCOUNT, objectMapper.writeValueAsString(account));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return getResponse(SUCCESS, "insert successful", account, SC_OK);
        }
        else{
            return getResponse(ERROR, "insert failed", null, SC_NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(@RequestParam(value = "accountId") String accountId) {
        Account account = accountManager.getAccount(accountId);
        if(account != null) {
            // publish rabbitmq event => account
            return getResponse(SUCCESS, "query successful", account, SC_OK);
        }
        else{
            return getResponse(ERROR, "query failed", null, SC_BAD_REQUEST);
        }
    }

}
