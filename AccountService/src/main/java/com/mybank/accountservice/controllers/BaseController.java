package com.mybank.accountservice.controllers;

import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;

import static com.mybank.accountservice.constants.AppConstants.*;

public class BaseController {
    protected ResponseEntity<?> getResponse(String statusType, String message, Object data, Integer status) {
        if (statusType.equals(ERROR))
            return ResponseEntity.status(status).body(
                    ImmutableMap.of(statusType, ImmutableMap.of(STATUS, status, MESSAGE, message, DATA, data)));

        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS_TYPE, statusType, STATUS, status, MESSAGE, message, DATA, data));
    }

    protected ResponseEntity<?> getResponse(String statusType, String message, Object data,  Integer size, Integer status) {
        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS_TYPE, statusType, STATUS, status, MESSAGE, message, DATA, data, TOTAL, size));
    }

    public ResponseEntity<?> getResponse(String statusType, String message, Integer status) {
        if (statusType.equals(ERROR))
            return ResponseEntity.status(status).body(
                    ImmutableMap.of(statusType, ImmutableMap.of(STATUS, status, MESSAGE, message)));

        return ResponseEntity.status(status).body(
                ImmutableMap.of(STATUS, status, STATUS_TYPE, statusType, MESSAGE, message));

    }
}
