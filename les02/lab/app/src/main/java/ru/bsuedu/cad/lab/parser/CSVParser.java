package ru.bsuedu.cad.lab.parser;

import ru.bsuedu.cad.lab.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {

    @Override
    public List<Product> parse(String data) {
        List<Product> products = new ArrayList<>();

        String[] lines = data.split("\\R");

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];

            if (line.isBlank()) {
                continue;
            }

            String[] values = line.split(",", -1);

            Product product = new Product(
                    Long.parseLong(values[0]),
                    values[1],
                    values[2],
                    Integer.parseInt(values[3]),
                    new BigDecimal(values[4]),
                    Integer.parseInt(values[5]),
                    values[6],
                    parseDate(values[7]),
                    parseDate(values[8])
            );

            products.add(product);
        }

        return products;
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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(value, formatter);
    }
}