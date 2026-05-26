package ru.bsuedu.cad.lab.dto;

import ru.bsuedu.cad.lab.entity.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {
    private Integer orderId;
    private String customerName;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private String status;
    private String shippingAddress;

    public OrderResponse(Order order) {
        this.orderId = order.getOrderId();
        this.customerName = order.getCustomer().getName();
        this.orderDate = order.getOrderDate();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        this.shippingAddress = order.getShippingAddress();
    }

    public Integer getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }
}