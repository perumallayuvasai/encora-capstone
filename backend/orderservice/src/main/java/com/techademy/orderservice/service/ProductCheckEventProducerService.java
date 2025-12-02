package com.techademy.orderservice.service;

import org.example.events.ProductCheckEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductCheckEventProducerService {

    public final KafkaTemplate<String, ProductCheckEvent> kafkaTemplate;

    public ProductCheckEventProducerService(
        KafkaTemplate<String, ProductCheckEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductCheckEvent(ProductCheckEvent event) {
        kafkaTemplate.send("product-check-event", event);
    }
}
