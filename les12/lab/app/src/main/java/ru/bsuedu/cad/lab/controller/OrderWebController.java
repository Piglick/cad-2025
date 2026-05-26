package ru.bsuedu.cad.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bsuedu.cad.lab.entity.Order;
import ru.bsuedu.cad.lab.service.OrderService;
import ru.bsuedu.cad.lab.service.ProductService;

@Controller
@RequestMapping("/orders")
public class OrderWebController {
    private final OrderService orderService;
    private final ProductService productService;

    public OrderWebController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "orders";
    }

    @GetMapping("/new")
    public String newOrderForm(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "order-form";
    }

    @PostMapping
    public String createOrder(@RequestParam("customerId") Integer customerId,
                              @RequestParam("productId") Integer productId,
                              @RequestParam("quantity") Integer quantity) {
        orderService.createOrder(customerId, productId, quantity);
        return "redirect:/orders";
    }

    @GetMapping("/{id}/edit")
    public String editOrderForm(@PathVariable("id") Integer id, Model model) {
        Order order = orderService.getOrderById(id);
        model.addAttribute("order", order);
        return "order-edit";
    }

    @PostMapping("/{id}/edit")
    public String updateOrder(@PathVariable("id") Integer id,
                              @RequestParam("status") String status,
                              @RequestParam("shippingAddress") String shippingAddress) {
        orderService.updateOrder(id, status, shippingAddress);
        return "redirect:/orders";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrder(@PathVariable("id") Integer id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }
}