package com.example.demo.elasticsearch.reposiotry;

import com.example.demo.elasticsearch.model.Bank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bankrepository extends MongoRepository<Bank,String> {
}
