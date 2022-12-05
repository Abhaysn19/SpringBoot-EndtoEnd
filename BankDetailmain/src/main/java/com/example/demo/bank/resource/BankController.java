package com.example.demo.bank.resource;

import com.example.demo.bank.model.Bank;
import com.example.demo.bank.repository.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BankController {
	@Autowired
	private KafkaTemplate kafkaTemplate;
	@Autowired
	 private BankRepository repository;
	private String topic="Bank"; 
	
	@PostMapping("/addBank")
	public String saveBank(@RequestBody Bank bank)
	{
		kafkaTemplate.send(topic,bank);
		repository.save(bank);
		return "added bank with id :" + bank.getId();
	}
	
	
	@GetMapping("/findAllBanks")
	public List<Bank> getBanks()
	{
		return repository.findAll();
	}
	
    @GetMapping("/findAllBanks/{id}")
	public Optional<Bank> getBank(@PathVariable int id)
      {
		return repository.findById(id);
		
	}
		@DeleteMapping("/delete/{id}")
		public String deleteBank(@PathVariable int id)
		{
			repository.deleteById(id);
			return "bank deleted with id :" + id;
		}
}
