package com.techademy.orderservice.service;

import com.techademy.orderservice.entity.Order;
import com.techademy.orderservice.entity.OrderItem;
import com.techademy.orderservice.repository.OrderRepository;
import java.math.BigDecimal;
import org.example.enums.StockCheckEventResponseType;
import org.example.events.OrderPlacedNotificationEvent;
import org.example.events.PlaceOrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlaceOrderEventConsumerService {

    private final OrderRepository orderRepository;
    private final NotificationProducerService notificationProducer;

    public PlaceOrderEventConsumerService(
        OrderRepository orderRepository,
        NotificationProducerService notificationProducer
    ) {
        this.orderRepository = orderRepository;
        this.notificationProducer = notificationProducer;
    }

    @KafkaListener(
        topics = "place-order-event-topic",
        groupId = "order-service-group"
    )
    @Transactional
    public void listenProductCheckEvents(PlaceOrderEvent message) {
        //  Stock Check Failed
        if (message.getResponseType() != StockCheckEventResponseType.SUCCESS) {
            System.err.println(
                "Order failed due to: " + message.getResponseType()
            );

            // Send FAILURE Notification
            OrderPlacedNotificationEvent failureEvent =
                new OrderPlacedNotificationEvent(
                    "N/A", // No Order ID since we didn't save it
                    message.getUserId(),
                    message.getProductName(),
                    BigDecimal.ZERO,
                    "FAILED: " + message.getResponseType() // e.g. "FAILED: INSUFFICIENT_STOCK"
                );
            notificationProducer.sendNotificationEvent(failureEvent);

            return;
        }

        // Success
        Order order = new Order();
        order.setUserId(message.getUserId());
        order.setStatus("CONFIRMED");

        BigDecimal price = message.getPrice() != null
            ? message.getPrice()
            : BigDecimal.ZERO;
        BigDecimal total = price.multiply(
            new BigDecimal(message.getQuantity())
        );
        order.setTotalAmount(total);

        OrderItem item = new OrderItem();
        item.setProductId(Long.parseLong(message.getProductId()));
        item.setQuantity(message.getQuantity());
        item.setSize(message.getProductSize().name());
        item.setPrice(price);
        item.setProductName(message.getProductName());
        item.setProductVariantId(message.getProductVariantId());

        order.addOrderItem(item);
        Order savedOrder = orderRepository.save(order);

        // Send SUCCESS Notification
        OrderPlacedNotificationEvent successEvent =
            new OrderPlacedNotificationEvent(
                savedOrder.getId().toString(),
                savedOrder.getUserId(),
                item.getProductName(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus()
            );

        notificationProducer.sendNotificationEvent(successEvent);
    }
}
