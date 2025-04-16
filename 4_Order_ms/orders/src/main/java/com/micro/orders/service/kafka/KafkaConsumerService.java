package com.micro.orders.service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.orders.dto.OrderDto;
import com.micro.orders.model.OrderEntity;
import com.micro.orders.repository.OrderJpaRepository;

@Service
public class KafkaConsumerService {

    private final OrderJpaRepository orderJpaRepository;

    public KafkaConsumerService(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    private static final String TOPIC = "product-topic";

    @KafkaListener(topics = TOPIC)
    public void consumeMessage(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            OrderDto orderDto = mapper.readValue(message, OrderDto.class);

            System.out.println("Received message: " + orderDto.toString());
            var order = mappingOrder(orderDto);
            orderJpaRepository.save(order);
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON message: " + message);
            e.printStackTrace();
        }
    }

    private OrderEntity mappingOrder(OrderDto orderDto) {
        OrderEntity order = new OrderEntity();
        order.setUserId(orderDto.getUserId());
        order.setCartId(orderDto.getCartId());
        return order;
    }
}
