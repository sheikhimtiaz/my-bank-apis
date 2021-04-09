package com.mybank.accountservice;

import com.mybank.accountservice.mappers.AccountMapper;
import com.mybank.accountservice.models.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@Mapper()
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
