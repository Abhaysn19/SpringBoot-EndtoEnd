package com.example.demo.customer.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.customer.model.Bank;
import com.example.demo.customer.model.Customer;
import com.example.demo.customer.model.OutputModel;
import com.example.demo.customer.repository.CustomerRepository;



@RestController
@EnableCaching
public class CustomerController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	 private CustomerRepository repository;
	
	@PostMapping("/customer")
	public String saveCustomer(@RequestBody Customer customer)
	{
		repository.save(customer);
		return "added Customer with id :" + customer.getCid();
	}
	
	
	@GetMapping("/findCust")
	public List<Customer> getCustomers()
	{
		return repository.findAll();
	}
	
    @GetMapping("/findAllCust/{id}")
    @Cacheable(key="#id",value="Customer")
	public OutputModel getCustomers(@PathVariable int id)
      {
    	Optional<Customer> customer= repository.findById(id);
    	RestTemplate resttemplate = new RestTemplate();
    	String uri = "http://localhost:8083/findAllBanks/{bid}";
    	Map<String, Integer> uriparam = new HashMap<>();
    	uriparam.put("bid", customer.get().getBid());
    	Bank bank = resttemplate.getForObject(uri, Bank.class, uriparam);
		OutputModel output = new OutputModel();
		output.setBid(customer.get().getBid());
		output.setBname(bank.getBname());
		output.setCname(customer.get().getCname());
		output.setCid(customer.get().getCid());
		return output;
	}
		@DeleteMapping("/deleteCust/{id}")
		public String deleteCustomers(@PathVariable int id)
		{
			repository.deleteById(id);
			return "bank deleted with id :" + id;
		}
}
