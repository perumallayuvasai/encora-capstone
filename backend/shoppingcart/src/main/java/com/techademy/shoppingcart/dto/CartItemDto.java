package com.techademy.shoppingcart.dto;

import org.example.enums.Size;

public class CartItemDto {
    private String productId;
    private Integer quantity;
    private Size size;

    public CartItemDto() {
    }

    public CartItemDto(String productId, Integer quantity, Size size) {
        this.productId = productId;
        this.quantity = quantity;
        this.size = size;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
