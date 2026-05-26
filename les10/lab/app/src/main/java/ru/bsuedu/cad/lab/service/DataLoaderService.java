package ru.bsuedu.cad.lab.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bsuedu.cad.lab.entity.Category;
import ru.bsuedu.cad.lab.entity.Customer;
import ru.bsuedu.cad.lab.entity.Product;
import ru.bsuedu.cad.lab.repository.CategoryRepository;
import ru.bsuedu.cad.lab.repository.CustomerRepository;
import ru.bsuedu.cad.lab.repository.ProductRepository;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DataLoaderService {
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public DataLoaderService(CategoryRepository categoryRepository,
                             CustomerRepository customerRepository,
                             ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public void loadData() {
        loadCategories();
        loadCustomers();
        loadProducts();
    }

    private void loadCategories() {
        String data = readResourceFile("category.csv");
        String[] lines = data.split("\\R");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];

            if (line.isBlank()) {
                continue;
            }

            String[] values = line.split(",", -1);

            Category category = new Category(
                    Integer.parseInt(values[0]),
                    values[1],
                    values[2]
            );

            categoryRepository.save(category);
        }
    }

    private void loadCustomers() {
        String data = readResourceFile("customer.csv");
        String[] lines = data.split("\\R");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];

            if (line.isBlank()) {
                continue;
            }

            String[] values = line.split(",", -1);

            Customer customer = new Customer(
                    Integer.parseInt(values[0]),
                    values[1],
                    values[2],
                    values[3],
                    values[4]
            );

            customerRepository.save(customer);
        }
    }

    private void loadProducts() {
        String data = readResourceFile("product.csv");
        String[] lines = data.split("\\R");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];

            if (line.isBlank()) {
                continue;
            }

            String[] values = line.split(",", -1);

            Integer categoryId = Integer.parseInt(values[3]);
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found: " + categoryId));

            Product product = new Product(
                    Integer.parseInt(values[0]),
                    values[1],
                    values[2],
                    category,
                    new BigDecimal(values[4]),
                    Integer.parseInt(values[5]),
                    values[6],
                    parseDate(values[7]),
                    parseDate(values[8])
            );

            productRepository.save(product);
        }
    }

    private String readResourceFile(String fileName) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }

    private LocalDateTime parseDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        if (value.contains("T")) {
            return LocalDateTime.parse(value);
        }

        if (value.length() == 10) {
            return LocalDate.parse(value).atStartOfDay();
        }

        return LocalDateTime.parse(value);
    }
}