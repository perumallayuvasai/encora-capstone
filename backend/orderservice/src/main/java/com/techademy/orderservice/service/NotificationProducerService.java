package com.techademy.orderservice.service;

import org.example.events.OrderPlacedNotificationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationProducerService {

    private final KafkaTemplate<
        String,
        OrderPlacedNotificationEvent
    > kafkaTemplate;
    private static final String TOPIC = "order-notification-topic";

    public NotificationProducerService(
        KafkaTemplate<String, OrderPlacedNotificationEvent> kafkaTemplate
    ) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotificationEvent(OrderPlacedNotificationEvent event) {
        // Use userId as key to ensure user notifications are ordered if needed
        kafkaTemplate.send(TOPIC, event.getUserId(), event);
        System.out.println(
            "Notification event sent for Order ID: " + event.getOrderId()
        );
    }
}
