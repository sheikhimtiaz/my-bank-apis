package com.mybank.ReportingService.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String accountId;
    private String customerId;
    private String country;
    private List<Balance> balances;

    public Account() {
    }

    public Account(String accountId, String customerId, String country, List<String> currencies) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.country = country;
        this.balances = new ArrayList<>();
        for(int i=0;i<currencies.size();i++)
        {
            this.balances.add(new Balance(currencies.get(i), 0, accountId));
        }
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Balance> getBalances() {
        return balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }
}
