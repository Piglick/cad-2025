package ru.bsuedu.cad.lab.dto;

public class UpdateOrderRequest {
    private String status;
    private String shippingAddress;

    public String getStatus() {
        return status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}