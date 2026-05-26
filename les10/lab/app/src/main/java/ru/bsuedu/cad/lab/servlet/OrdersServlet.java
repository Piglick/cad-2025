package ru.bsuedu.cad.lab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.bsuedu.cad.lab.entity.Order;
import ru.bsuedu.cad.lab.service.OrderService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        orderService = context.getBean(OrderService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html; charset=UTF-8");

        List<Order> orders = orderService.getAllOrders();

        PrintWriter writer = response.getWriter();

        writer.println("""
                <!DOCTYPE html>
                <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <title>Orders</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 30px; }
                        table { border-collapse: collapse; width: 100%; margin-top: 20px; }
                        th, td { border: 1px solid #999; padding: 8px; text-align: left; }
                        th { background-color: #eeeeee; }
                        a.button {
                            display: inline-block;
                            padding: 10px 15px;
                            background: #2d6cdf;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }
                    </style>
                </head>
                <body>
                    <h1>Список заказов</h1>
                    <a class="button" href="create-order">Создать заказ</a>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Покупатель</th>
                            <th>Дата</th>
                            <th>Сумма</th>
                            <th>Статус</th>
                            <th>Адрес доставки</th>
                        </tr>
                """);

        for (Order order : orders) {
            writer.println("<tr>");
            writer.println("<td>" + order.getOrderId() + "</td>");
            writer.println("<td>" + order.getCustomer().getName() + "</td>");
            writer.println("<td>" + order.getOrderDate() + "</td>");
            writer.println("<td>" + order.getTotalPrice() + "</td>");
            writer.println("<td>" + order.getStatus() + "</td>");
            writer.println("<td>" + order.getShippingAddress() + "</td>");
            writer.println("</tr>");
        }

        writer.println("""
                    </table>
                </body>
                </html>
                """);
    }
}