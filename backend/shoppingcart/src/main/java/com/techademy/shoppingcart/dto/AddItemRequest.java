package com.techademy.shoppingcart.dto;

import org.example.enums.Size;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddItemRequest {
    @NotBlank
    private String productId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private Size size;

    public AddItemRequest() {
    }

    public AddItemRequest(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
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
