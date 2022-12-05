package com.example.demo.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CustomerDetailsmainApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDetailsmainApplication.class, args);
	}

}
