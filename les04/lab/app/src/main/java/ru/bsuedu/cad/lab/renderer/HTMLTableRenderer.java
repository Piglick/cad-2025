package ru.bsuedu.cad.lab.renderer;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.bsuedu.cad.lab.model.Product;
import ru.bsuedu.cad.lab.provider.ProductProvider;

import java.io.IOException;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@Primary
public class HTMLTableRenderer implements Renderer {
    private final ProductProvider provider;

    public HTMLTableRenderer(ProductProvider provider) {
        this.provider = provider;
    }

    @Override
    public void render() {
        List<Product> products = provider.getProducts();

        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"ru\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <title>Products</title>\n");
        html.append("    <style>\n");
        html.append("        table { border-collapse: collapse; width: 100%; }\n");
        html.append("        th, td { border: 1px solid black; padding: 8px; text-align: left; }\n");
        html.append("        th { background-color: #f2f2f2; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Товары для зоомагазина</h1>\n");
        html.append("    <table>\n");
        html.append("        <tr>\n");
        html.append("            <th>ID</th>\n");
        html.append("            <th>Name</th>\n");
        html.append("            <th>Description</th>\n");
        html.append("            <th>Category</th>\n");
        html.append("            <th>Price</th>\n");
        html.append("            <th>Quantity</th>\n");
        html.append("        </tr>\n");

        for (Product product : products) {
            html.append("        <tr>\n");
            html.append("            <td>").append(product.getProductId()).append("</td>\n");
            html.append("            <td>").append(product.getName()).append("</td>\n");
            html.append("            <td>").append(product.getDescription()).append("</td>\n");
            html.append("            <td>").append(product.getCategoryId()).append("</td>\n");
            html.append("            <td>").append(product.getPrice()).append("</td>\n");
            html.append("            <td>").append(product.getStockQuantity()).append("</td>\n");
            html.append("        </tr>\n");
        }

        html.append("    </table>\n");
        html.append("</body>\n");
        html.append("</html>");

        try {
            Files.writeString(
                    Path.of("products.html"),
                    html.toString(),
                    StandardCharsets.UTF_8
            );
            System.out.println("HTML table was saved to products.html");
        } catch (IOException e) {
            throw new RuntimeException("Error writing HTML file", e);
        }
    }
}