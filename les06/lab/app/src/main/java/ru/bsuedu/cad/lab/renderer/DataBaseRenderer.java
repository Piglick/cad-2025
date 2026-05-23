package ru.bsuedu.cad.lab.renderer;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.bsuedu.cad.lab.model.Category;
import ru.bsuedu.cad.lab.model.Product;
import ru.bsuedu.cad.lab.provider.CategoryProvider;
import ru.bsuedu.cad.lab.provider.ProductProvider;
import ru.bsuedu.cad.lab.request.CategoryRequest;

import java.sql.Timestamp;
import java.util.List;

@Component
@Primary
public class DataBaseRenderer implements Renderer {
    private final ProductProvider productProvider;
    private final CategoryProvider categoryProvider;
    private final JdbcTemplate jdbcTemplate;
    private final CategoryRequest categoryRequest;

    public DataBaseRenderer(ProductProvider productProvider,
                            CategoryProvider categoryProvider,
                            JdbcTemplate jdbcTemplate,
                            CategoryRequest categoryRequest) {
        this.productProvider = productProvider;
        this.categoryProvider = categoryProvider;
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRequest = categoryRequest;
    }

    @Override
    public void render() {
        saveCategories();
        saveProducts();

        System.out.println("Data was saved to database");

        categoryRequest.printCategoriesWithMoreThanOneProduct();
    }

    private void saveCategories() {
        List<Category> categories = categoryProvider.getCategories();

        for (Category category : categories) {
            jdbcTemplate.update(
                    """
                    INSERT INTO CATEGORIES (category_id, name, description)
                    VALUES (?, ?, ?)
                    """,
                    category.getCategoryId(),
                    category.getName(),
                    category.getDescription()
            );
        }
    }

    private void saveProducts() {
        List<Product> products = productProvider.getProducts();

        for (Product product : products) {
            jdbcTemplate.update(
                    """
                    INSERT INTO PRODUCTS (
                        product_id,
                        name,
                        description,
                        category_id,
                        price,
                        stock_quantity,
                        image_url,
                        created_at,
                        updated_at
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """,
                    product.getProductId(),
                    product.getName(),
                    product.getDescription(),
                    product.getCategoryId(),
                    product.getPrice(),
                    product.getStockQuantity(),
                    product.getImageUrl(),
                    Timestamp.valueOf(product.getCreatedAt()),
                    Timestamp.valueOf(product.getUpdatedAt())
            );
        }
    }
}