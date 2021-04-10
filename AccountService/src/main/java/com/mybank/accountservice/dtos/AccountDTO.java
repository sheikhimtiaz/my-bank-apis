package com.mybank.accountservice.dtos;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.*;

public class AccountDTO {
    private String customerId;
    private String country;
    private List<String> currencies;

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

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
}
