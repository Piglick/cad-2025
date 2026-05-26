package ru.bsuedu.cad.lab.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.bsuedu.cad.lab.entity.Product;
import ru.bsuedu.cad.lab.service.ProductService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/api/products")
public class ProductRestServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context =
                WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        productService = context.getBean(ProductService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json; charset=UTF-8");

        List<Product> products = productService.getAllProducts();

        PrintWriter writer = response.getWriter();

        writer.println("[");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            writer.println("  {");
            writer.println("    \"name\": \"" + escapeJson(product.getName()) + "\",");
            writer.println("    \"category\": \"" + escapeJson(product.getCategory().getName()) + "\",");
            writer.println("    \"stockQuantity\": " + product.getStockQuantity());
            writer.print("  }");

            if (i < products.size() - 1) {
                writer.println(",");
            } else {
                writer.println();
            }
        }
        writer.println("]");
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"");
    }
}