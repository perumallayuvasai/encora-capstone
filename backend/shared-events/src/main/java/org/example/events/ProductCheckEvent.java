package org.example.events;

import java.math.BigDecimal;
import org.example.enums.Size;

public class ProductCheckEvent {

    private String productId;
    private Size size;
    private String userId;
    private int quantity;
    private String productName;
    private BigDecimal price;
    private Long productVariantId;

    public ProductCheckEvent() {}

    public ProductCheckEvent(
        String productId,
        Size size,
        int quantity,
        String userId,
        String productName,
        BigDecimal price,
        Long productVariantId
    ) {
        this.productId = productId;
        this.userId = userId;
        this.size = size;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
        this.productVariantId = productVariantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }
}
