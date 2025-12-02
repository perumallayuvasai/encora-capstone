package com.techademy.orderservice.service;

import org.example.events.ProductCheckEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PlaceOrderEventConsumerService {

    @KafkaListener(topics = "place-order-id", groupId = "order-service-group")
    public void listenProductCheckEvents(ProductCheckEvent message) {
        System.out.println("Received ProductCheckEvent: " + message);
    }

}
