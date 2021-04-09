package com.mybank.accountservice.models;

public class Balance{
    String currency;
    String amount;
    String accountId;

    public Balance() {
    }

    public Balance(String currency, String amount, String accountId) {
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}