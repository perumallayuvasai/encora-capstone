package com.techademy.orderservice.controller;

import org.example.events.ProductCheckEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.techademy.orderservice.dto.OrderItemRequestDto;
import com.techademy.orderservice.dto.OrderRequestDto;
import com.techademy.orderservice.service.OrderService;
import com.techademy.orderservice.service.ProductCheckEventProducerService;

@RestController
@RequestMapping()
public class OrderController {

    private final OrderService orderService;

    private final ProductCheckEventProducerService productCheckEventProducerService;

    public OrderController(OrderService orderService,
            ProductCheckEventProducerService productCheckEventProducerService) {
        this.orderService = orderService;
        this.productCheckEventProducerService = productCheckEventProducerService;
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@RequestBody OrderItemRequestDto orderRequest) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        ProductCheckEvent productCheckEvent = new ProductCheckEvent();
        productCheckEvent.setProductId(orderRequest.getProductId().toString());
        productCheckEvent.setSize(orderRequest.getSize());
        productCheckEvent.setQuantity(orderRequest.getQuantity());
        productCheckEvent.setUserId(userId);

        productCheckEventProducerService.sendProductCheckEvent(productCheckEvent);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order placed successfully");
    }
}
