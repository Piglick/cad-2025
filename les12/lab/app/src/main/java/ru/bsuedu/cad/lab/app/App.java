package ru.bsuedu.cad.lab.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.bsuedu.cad.lab.config.AppConfig;
import ru.bsuedu.cad.lab.entity.Order;
import ru.bsuedu.cad.lab.service.DataLoaderService;
import ru.bsuedu.cad.lab.service.OrderService;

import java.util.List;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        DataLoaderService dataLoaderService = context.getBean(DataLoaderService.class);
        OrderService orderService = context.getBean(OrderService.class);

        dataLoaderService.loadData();
        logger.info("Initial data loaded successfully");

        Order order = orderService.createOrder(1, 1, 2);
        logger.info("Order created. ID: {}, total price: {}, status: {}",
                order.getOrderId(),
                order.getTotalPrice(),
                order.getStatus()
        );

        List<Order> orders = orderService.getAllOrders();
        logger.info("Orders in database: {}", orders.size());

        for (Order savedOrder : orders) {
            logger.info("Saved order. ID: {}, total price: {}, status: {}",
                    savedOrder.getOrderId(),
                    savedOrder.getTotalPrice(),
                    savedOrder.getStatus()
            );
        }

        System.out.println("JPA application finished successfully");
    }
}