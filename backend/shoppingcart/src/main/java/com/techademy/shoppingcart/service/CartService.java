package com.techademy.shoppingcart.service;

import com.techademy.shoppingcart.dto.*;
import com.techademy.shoppingcart.entity.Cart;
import com.techademy.shoppingcart.entity.CartItem;
import org.example.enums.Size;
import com.techademy.shoppingcart.exception.NotFoundException;
import com.techademy.shoppingcart.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository repo) {
        this.cartRepository = repo;
    }

    public CartDto getCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> new Cart(userId));
        return toDto(cart);
    }

    public CartDto addItem(String userId, AddItemRequest req) {
        Cart cart = cartRepository.findByUserId(userId).orElseGet(() -> new Cart(userId));

        CartItem item = new CartItem(req.getProductId(), req.getQuantity(), req.getSize());
        cart.addOrUpdateItem(item);

        Cart saved = cartRepository.save(cart);
        return toDto(saved);
    }

    public CartDto updateItem(String userId, String productId, Size size, UpdateItemRequest req) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found for user: " + userId));

        if (req.getQuantity() <= 0) {
            cart.removeItem(productId, size);
        } else {
            boolean found = false;
            for (CartItem it : cart.getItems()) {
                if (it.getProductId().equals(productId) && it.getSize() == size) {
                    it.setQuantity(req.getQuantity());
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new NotFoundException("Item not found in cart");
            }
        }
        Cart saved = cartRepository.save(cart);
        return toDto(saved);
    }

    public void removeItem(String userId, String productId, Size size) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found for user: " + userId));
        cart.removeItem(productId, size);
        cartRepository.save(cart);
    }

    public void clearCart(String userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException("Cart not found for user: " + userId));
        cart.clear();
        cartRepository.save(cart);
    }

    private CartDto toDto(Cart cart) {
        return new CartDto(
                cart.getUserId(),
                cart.getItems().stream()
                        .map(i -> new CartItemDto(i.getProductId(), i.getQuantity(), i.getSize()))
                        .collect(Collectors.toList()));
    }
}
