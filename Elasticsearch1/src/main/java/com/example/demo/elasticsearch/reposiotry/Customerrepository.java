package com.example.demo.elasticsearch.reposiotry;

import com.example.demo.elasticsearch.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customerrepository extends MongoRepository<Customer, String> {

//	List<Customer> findByFirstname(String firstName);

}
