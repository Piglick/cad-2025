package ru.bsuedu.cad.lab.dto;

public class CreateOrderRequest {
    private Integer customerId;
    private Integer productId;
    private Integer quantity;

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}