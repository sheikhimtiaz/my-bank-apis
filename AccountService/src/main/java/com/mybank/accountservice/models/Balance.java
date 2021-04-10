package com.mybank.accountservice.models;

public class Balance{
    int id;
    String currency;
    double amount;
    String accountId;

    public Balance() {
    }

    public Balance(String currency, double amount, String accountId) {
        this.currency = currency;
        this.amount = amount;
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}