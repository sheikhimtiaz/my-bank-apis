package com.mybank.ReportingService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction implements Serializable {
    private String accountId;
    private String transactionId;
    private String currency;
    private String direction;
    private String description;
    private double amount;
    private double balanceAfterTransaction;

}
