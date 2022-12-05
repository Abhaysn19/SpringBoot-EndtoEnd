package com.example.demo.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.customer.model.Customer;


    @Repository
	public interface CustomerRepository extends MongoRepository<Customer, Integer>
	{

	}
