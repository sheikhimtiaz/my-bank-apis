package com.mybank.accountservice.configs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomizedMessageException extends RuntimeException
{
    public CustomizedMessageException(String message)
    {
        super(message);
    }
}