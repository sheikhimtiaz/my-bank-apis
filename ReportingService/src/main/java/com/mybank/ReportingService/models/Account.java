package com.mybank.ReportingService.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Account implements Serializable {
    private String accountId;
    private String customerId;
    private String country;
    private List<Balance> balances;
}
