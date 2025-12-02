package com.example.product_service.service;

import org.example.enums.StockCheckEventResponseType;
import org.example.events.PlaceOrderEvent;
import org.example.events.ProductCheckEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductCheckEventListener {

    private final ProductService productService;
    private final PlaceOrderEventProducerService producerService;

    public ProductCheckEventListener(
        ProductService productService,
        PlaceOrderEventProducerService producerService
    ) {
        this.productService = productService;
        this.producerService = producerService;
    }

    @KafkaListener(
        topics = "product-check-event",
        groupId = "product-service-group"
    )
    public void handleProductCheckEvent(ProductCheckEvent event) {
        System.out.println(
            "Received ProductCheckEvent for Product ID: " + event.getProductId()
        );

        // 1. Call the service logic we added in Step 1
        StockCheckEventResponseType responseType =
            productService.checkAndReduceStock(
                event.getProductId(),
                event.getSize(),
                event.getQuantity()
            );

        // Log the response type
        System.out.println(
            "Stock check result for Product ID " +
                event.getProductId() +
                ": " +
                responseType
        );

        // 2. Prepare response event
        PlaceOrderEvent responseEvent = new PlaceOrderEvent();
        responseEvent.setProductId(event.getProductId());
        responseEvent.setProductSize(event.getSize());
        responseEvent.setQuantity(event.getQuantity());
        responseEvent.setResponseType(responseType);
        responseEvent.setUserId(event.getUserId());
        responseEvent.setProductName(event.getProductName());
        responseEvent.setPrice(event.getPrice());
        responseEvent.setProductVariantId(event.getProductVariantId());

        System.out.println(
            "Prepared PlaceOrderEvent response for Product ID: " +
                event.getProductId() +
                " with response type: " +
                responseType
        );

        // 3. Send response back to Order Service
        producerService.sendPlaceOrderEvent(responseEvent);
    }
}
