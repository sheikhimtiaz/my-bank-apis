package com.mybank.accountservice.controllers;

import com.mybank.accountservice.dtos.TransactionDTO;
import com.mybank.accountservice.managers.BalanceTransactionManager;
import com.mybank.accountservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mybank.accountservice.constants.AppConstants.*;

@RestController
@RequestMapping("/api")
public class TransactionController extends BaseController {

    @Autowired
    BalanceTransactionManager balanceTransactionManager;

    @PostMapping("/transaction")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDTO data) {
        Transaction transaction = balanceTransactionManager.createTransaction(data);
        if(transaction != null) {
            return getResponse("success", "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", null, SC_NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/transaction")
    public ResponseEntity<?> getTransaction(@RequestParam(value = "transactionId", defaultValue = "World") String transactionId) {
        Transaction transaction = balanceTransactionManager.getTransaction(transactionId);
        if(transaction != null) {
            return getResponse("success", "insert successful", transaction, SC_OK);
        }
        else{
            return getResponse("failed", "insert failed", null, SC_BAD_REQUEST);
        }
    }
}
