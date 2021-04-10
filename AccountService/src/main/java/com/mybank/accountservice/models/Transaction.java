package com.mybank.accountservice.models;

import com.mybank.accountservice.dtos.TransactionDTO;

public class Transaction {
    private String accountId;
    private String transactionId;
    private String currency;
    private String direction;
    private String description;
    private double amount;
    private double balanceAfterTransaction;

    public Transaction() {
    }

    public Transaction(TransactionDTO transactionDTO) {
        this.accountId = transactionDTO.getAccountId();
        this.amount = transactionDTO.getAmount();
        this.currency = transactionDTO.getCurrency();
        this.direction = transactionDTO.getDirection();
        this.description = transactionDTO.getDescription();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
}
