package org.example.events;

import java.math.BigDecimal;
import org.example.enums.Size;
import org.example.enums.StockCheckEventResponseType;

public class PlaceOrderEvent {

    private String userId;
    private String productId;
    private int quantity;
    private StockCheckEventResponseType responseType;
    private Size productSize;
    private String productName;
    private BigDecimal price;
    private Long productVariantId;

    public PlaceOrderEvent() {}

    public PlaceOrderEvent(
        String userId,
        String productId,
        Size productSize,
        int quantity,
        StockCheckEventResponseType responseType,
        String productName,
        BigDecimal price,
        Long productVariantId
    ) {
        this.userId = userId;
        this.productId = productId;
        this.productSize = productSize;
        this.quantity = quantity;
        this.responseType = responseType;
        this.productName = productName;
        this.price = price;
        this.productVariantId = productVariantId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public StockCheckEventResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(StockCheckEventResponseType responseType) {
        this.responseType = responseType;
    }

    public Size getProductSize() {
        return productSize;
    }

    public void setProductSize(Size productSize) {
        this.productSize = productSize;
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
