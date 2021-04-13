package com.mybank.accountservice.dtos;

import javax.validation.constraints.NotNull;

public class TransactionDTO {
    @NotNull(message = "accountId cannot be null")
    private String accountId;

    @NotNull(message = "amount cannot be null")
    private double amount;

    @NotNull(message = "currency cannot be null")
    private String currency;

    @NotNull(message = "direction cannot be null")
    private String direction;

    @NotNull(message = "description cannot be null")
    private String description;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
