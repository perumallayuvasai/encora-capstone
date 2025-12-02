package org.example.events;

import java.math.BigDecimal;

public class OrderPlacedNotificationEvent {
    private String orderId;
    private String userId;
    private String productName;
    private BigDecimal totalAmount;
    private String status;

    public OrderPlacedNotificationEvent() {
    }

    public OrderPlacedNotificationEvent(String orderId, String userId, String productName, BigDecimal totalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.productName = productName;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    // Getters and Setters
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
