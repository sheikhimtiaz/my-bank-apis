package com.mybank.accountservice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Mapper()
@SpringBootApplication
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
