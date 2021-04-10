package com.mybank.accountservice.controllers;

import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.managers.TransactionManager;
import com.mybank.accountservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mybank.accountservice.constants.AppConstants.SC_OK;
import static com.mybank.accountservice.constants.AppConstants.SC_SERVER_ERROR;

public class TransactionController extends BaseController {
    @Autowired
    TransactionManager transactionManager;

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO data) {
        Transaction transaction = transactionManager.createTransaction(data);
        if(transaction != null) {
            return getResponse("success", "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", null, SC_SERVER_ERROR);
        }
    }

    @GetMapping("/transaction")
    public ResponseEntity<?> getTransaction(@RequestParam(value = "transactionId", defaultValue = "World") String transactionId) {
        Transaction transaction = transactionManager.getTransaction(transactionId);
        if(transaction != null) {
            return getResponse("success", "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", null, SC_SERVER_ERROR);
        }
    }
}
