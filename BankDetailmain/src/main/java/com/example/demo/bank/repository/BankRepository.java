package com.example.demo.bank.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.bank.model.Bank;




public interface BankRepository extends MongoRepository<Bank, Integer>
{

}
