package ru.bsuedu.cad.lab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.bsuedu.cad.lab.entity.Product;
import ru.bsuedu.cad.lab.service.OrderService;
import ru.bsuedu.cad.lab.service.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/create-order")
public class CreateOrderServlet extends HttpServlet {
    private OrderService orderService;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        orderService = context.getBean(OrderService.class);
        productService = context.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/html; charset=UTF-8");

        List<Product> products = productService.getAllProducts();

        PrintWriter writer = response.getWriter();

        writer.println("""
                <!DOCTYPE html>
                <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <title>Create Order</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 30px; }
                        form { width: 400px; }
                        label { display: block; margin-top: 15px; }
                        input, select {
                            width: 100%;
                            padding: 8px;
                            margin-top: 5px;
                        }
                        button {
                            margin-top: 20px;
                            padding: 10px 15px;
                            background: #2d6cdf;
                            color: white;
                            border: none;
                            border-radius: 5px;
                            cursor: pointer;
                        }
                        a { display: inline-block; margin-top: 15px; }
                    </style>
                </head>
                <body>
                    <h1>Создание заказа</h1>
                    <form method="post" action="create-order">
                        <label>ID покупателя</label>
                        <input type="number" name="customerId" value="1" required>
                        
                        <label>Товар</label>
                        <select name="productId">
                """);

        for (Product product : products) {
            writer.println("<option value=\"" + product.getProductId() + "\">"
                    + product.getName()
                    + " — " + product.getPrice()
                    + "</option>");
        }

        writer.println("""
                        </select>
                        
                        <label>Количество</label>
                        <input type="number" name="quantity" value="1" min="1" required>
                        
                        <button type="submit">Создать заказ</button>
                    </form>
                    
                    <a href="orders">Вернуться к списку заказов</a>
                </body>
                </html>
                """);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());

        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        Integer productId = Integer.parseInt(request.getParameter("productId"));
        Integer quantity = Integer.parseInt(request.getParameter("quantity"));

        orderService.createOrder(customerId, productId, quantity);

        response.sendRedirect("orders");
    }
}