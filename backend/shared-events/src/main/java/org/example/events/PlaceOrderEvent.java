package org.example.events;

import org.example.enums.Size;
import org.example.enums.StockCheckEventResponseType;

public class PlaceOrderEvent {

  private String productId;
  private int quantity;
  private StockCheckEventResponseType responseType;
  private Size productSize;

  public PlaceOrderEvent() {
  }

  public PlaceOrderEvent(String productId, Size productSize, int quantity, StockCheckEventResponseType responseType) {
    this.productId = productId;
    this.productSize = productSize;
    this.quantity = quantity;
    this.responseType = responseType;
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
}
