package ru.bsuedu.cad.lab.renderer;

import ru.bsuedu.cad.lab.model.Product;
import ru.bsuedu.cad.lab.provider.ProductProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleTableRenderer implements Renderer {
    private final ProductProvider provider;

    public ConsoleTableRenderer(ProductProvider provider) {
        this.provider = provider;
    }

    @Override
    public void render() {
        List<Product> products = provider.getProducts();

        printLine();
        System.out.printf("| %-3s | %-25s | %-10s | %-8s | %-10s |%n",
                "ID", "Name", "Category", "Price", "Quantity");
        printLine();

        for (Product product : products) {
            System.out.printf("| %-3d | %-25s | %-10d | %-8s | %-10d |%n",
                    product.getProductId(),
                    cut(product.getName(), 25),
                    product.getCategoryId(),
                    product.getPrice(),
                    product.getStockQuantity());
        }

        printLine();
    }

    private void printLine() {
        System.out.println("+-----+---------------------------+------------+----------+------------+");
    }

    private String cut(String value, int maxLength) {
        if (value == null) {
            return "";
        }

        if (value.length() <= maxLength) {
            return value;
        }

        return value.substring(0, maxLength - 3) + "...";
    }
}