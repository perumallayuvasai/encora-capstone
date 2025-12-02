package com.techademy.shoppingcart.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.example.enums.Size;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @Column(name = "cart_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @Column(name = "user_id", nullable = false, length = 100, unique = true)
    private String userId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CartItem> items = new ArrayList<>();

    public Cart() {
    }

    public Cart(String userId) {
        this.userId = userId;
    }

    public void addOrUpdateItem(CartItem item) {
        for (CartItem it : items) {
            if (it.getProductId().equals(item.getProductId()) && it.getSize() == item.getSize()) {
                it.setQuantity(it.getQuantity() + item.getQuantity());
                return;
            }
        }
        item.setCart(this);
        items.add(item);
    }

    public void removeItem(String productId, Size size) {
        items.removeIf(i -> i.getProductId().equals(productId) && i.getSize() == size);
    }

    public void clear() {
        items.clear();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }
}
