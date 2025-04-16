package com.micro.shop.service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.shop.model.client.Order;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private String TOPIC = "product-topic";

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Order order) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonOrder = mapper.writeValueAsString(order);
            kafkaTemplate.send(TOPIC, jsonOrder);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting order to JSON", e);
        }
    }
}
