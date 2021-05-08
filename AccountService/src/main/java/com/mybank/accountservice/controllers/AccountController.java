package com.mybank.accountservice.controllers;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.accountservice.dtos.AccountDTO;
import com.mybank.accountservice.managers.AccountManager;
import com.mybank.accountservice.managers.BalanceTransactionManager;
import com.mybank.accountservice.models.Account;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;

import static com.mybank.accountservice.constants.AppConstants.*;

@RestController
@RequestMapping("/api")
public class AccountController extends BaseController {

    AccountManager accountManager;

    private RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();
    private Validator validator;

    AccountController(AccountManager accountManager,
                      RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.accountManager = accountManager;
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a user.", notes = "Returns the user info.")
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
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get user info by userId", notes = "Returns the user info.")
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
