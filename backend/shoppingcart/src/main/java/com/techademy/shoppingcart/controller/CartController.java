package com.techademy.shoppingcart.controller;

import com.techademy.shoppingcart.dto.*;
import org.example.enums.Size;
import com.techademy.shoppingcart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping()
@Validated
public class CartController {
    private final CartService service;

    public CartController(CartService svc) {
        this.service = svc;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public ResponseEntity<CartDto> getCart() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.getCart(userId));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/items")
    public ResponseEntity<CartDto> addItem(@Valid @RequestBody AddItemRequest req) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.addItem(userId, req));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/items/{productId}")
    public ResponseEntity<CartDto> updateItem(
            @PathVariable String productId,
            @RequestParam Size size,
            @Valid @RequestBody UpdateItemRequest req) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(service.updateItem(userId, productId, size, req));
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/items/{productId}")
    public ResponseEntity<Void> removeItem(
            @PathVariable String productId,
            @RequestParam Size size) {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        service.removeItem(userId, productId, size);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/")
    public ResponseEntity<Void> clearCart() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        service.clearCart(userId);
        return ResponseEntity.noContent().build();
    }
}
