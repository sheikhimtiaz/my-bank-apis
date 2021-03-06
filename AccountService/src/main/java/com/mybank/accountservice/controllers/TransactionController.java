package com.mybank.accountservice.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.managers.BalanceTransactionManager;
import com.mybank.accountservice.models.Transaction;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.mybank.accountservice.constants.AppConstants.*;

@RestController
@RequestMapping("/api")
public class TransactionController extends BaseController {

    BalanceTransactionManager balanceTransactionManager;

    private RabbitTemplate rabbitTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

    TransactionController(BalanceTransactionManager balanceTransactionManager,
                          RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.balanceTransactionManager = balanceTransactionManager;
    }


    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a transaction.", notes = "Returns the user info.")
    public ResponseEntity<?> createTransaction(@Valid @RequestBody TransactionDTO data) {
        Transaction transaction = balanceTransactionManager.createTransaction(data);
        if(transaction != null) {
            try {
                rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, ROUTING_KEY_TRANSACTION, objectMapper.writeValueAsString(transaction));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return getResponse(SUCCESS, "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse(ERROR, "insert failed", null, SC_NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/transaction")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get transaction info by transactionId", notes = "Returns the transaction info.")
    public ResponseEntity<?> getTransaction(@RequestParam(value = "transactionId") String transactionId) {
        Transaction transaction = balanceTransactionManager.getTransaction(transactionId);
        if(transaction != null) {
            return getResponse(SUCCESS, "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse(ERROR, "insert failed", null, SC_BAD_REQUEST);
        }
    }
}
