package ru.bsuedu.cad.lab.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CategoryRequest {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRequest.class);

    private final JdbcTemplate jdbcTemplate;

    public CategoryRequest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void printCategoriesWithMoreThanOneProduct() {
        String sql = """
                SELECT c.name, COUNT(p.product_id) AS product_count
                FROM CATEGORIES c
                JOIN PRODUCTS p ON c.category_id = p.category_id
                GROUP BY c.category_id, c.name
                HAVING COUNT(p.product_id) > 1
                """;

        jdbcTemplate.query(sql, rs -> {
            String categoryName = rs.getString("name");
            int productCount = rs.getInt("product_count");

            logger.info("Category: {}, product count: {}", categoryName, productCount);
        });
    }
}