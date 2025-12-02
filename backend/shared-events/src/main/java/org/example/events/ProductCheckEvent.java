package org.example.events;

import org.example.enums.Size;

public class ProductCheckEvent {
  private String productId;
  private Size size;
  private String userId;
  private int quantity;

  public ProductCheckEvent() {
  }

  public ProductCheckEvent(String productId, Size size, int quantity, String userId) {
    this.productId = productId;
    this.userId = userId;
    this.size = size;
    this.quantity = quantity;
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
}
