package com.example.product_service.service;

import org.example.events.PlaceOrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlaceOrderEventProducerService {

    private final KafkaTemplate<String, PlaceOrderEvent> kafkaTemplate;
    private static final String RESPONSE_TOPIC = "place-order-event-topic";

    public PlaceOrderEventProducerService(KafkaTemplate<String, PlaceOrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPlaceOrderEvent(PlaceOrderEvent event) {
        kafkaTemplate.send(RESPONSE_TOPIC, event.getProductId(), event);
        System.out.println("Sent PlaceOrderEvent response for Product ID: " + event.getProductId());
    }
}
