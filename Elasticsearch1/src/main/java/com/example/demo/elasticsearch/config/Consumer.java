package com.example.demo.elasticsearch.config;


import com.example.demo.elasticsearch.model.Bank;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class Consumer {

    @Bean
    public ConsumerFactory<String, Bank> consumerFactory(){
        Map<String,Object> configs=new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG,"Customer-1");
        configs.put(JsonDeserializer.TRUSTED_PACKAGES,"com.example.ElasticsearchService.model");

        return new DefaultKafkaConsumerFactory<String,Bank>(configs,new StringDeserializer(),new ErrorHandlingDeserializer<>(new JsonDeserializer<>(Bank.class,false)));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Bank> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,Bank> factory=new ConcurrentKafkaListenerContainerFactory<String,Bank>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}