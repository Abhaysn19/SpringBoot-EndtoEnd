package com.example.demo.elasticsearch;

import com.example.demo.elasticsearch.model.Bank;
import com.example.demo.elasticsearch.model.Customer;
import com.example.demo.elasticsearch.reposiotry.Bankrepository;
import com.example.demo.elasticsearch.reposiotry.Customerrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class Elasticsearch1Application {
    @Autowired
    private Customerrepository repository;
    @Autowired
    Bankrepository repo;
    @KafkaListener(groupId = "Customer-1", topics="Bank", containerFactory="kafkaListenerContainerFactory")
    public void addCustomer(Bank bank) { repo.save(bank); }
    @PostMapping("/saveCustomer")
    public int saveCustomer(@RequestBody List<Customer> customers)
    {
    	repository.saveAll(customers);
    	return customers.size();
    }
    
    @GetMapping("/findAll")
    public Iterable <Customer> findAllCustomers()
    {
    	return repository.findAll();
    }
//    @GetMapping("/findByFName/{firstName}")
//    public List<Customer> findByFirstName(@PathVariable String firstName)
//    {
//    	return repository.findByFirstname(firstName);
//    }
    
	public static void main(String[] args) {
		SpringApplication.run(Elasticsearch1Application.class, args);
	}

}
