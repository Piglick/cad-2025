package ru.bsuedu.cad.lab.controller;

import org.springframework.web.bind.annotation.*;
import ru.bsuedu.cad.lab.dto.CreateOrderRequest;
import ru.bsuedu.cad.lab.dto.OrderResponse;
import ru.bsuedu.cad.lab.dto.UpdateOrderRequest;
import ru.bsuedu.cad.lab.entity.Order;
import ru.bsuedu.cad.lab.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderResponse> getAllOrders() {
        return orderService.getAllOrders()
                .stream()
                .map(OrderResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(@PathVariable Integer id) {
        return new OrderResponse(orderService.getOrderById(id));
    }

    @PostMapping
    public OrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        Order order = orderService.createOrder(
                request.getCustomerId(),
                request.getProductId(),
                request.getQuantity()
        );

        return new OrderResponse(order);
    }

    @PutMapping("/{id}")
    public OrderResponse updateOrder(@PathVariable Integer id,
                                     @RequestBody UpdateOrderRequest request) {
        Order order = orderService.updateOrder(
                id,
                request.getStatus(),
                request.getShippingAddress()
        );

        return new OrderResponse(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}