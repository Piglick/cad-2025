package ru.bsuedu.cad.lab.parser;

import org.springframework.stereotype.Component;
import ru.bsuedu.cad.lab.model.Category;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryCSVParser {

    public List<Category> parse(String data) {
        List<Category> categories = new ArrayList<>();

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

            categories.add(category);
        }

        return categories;
    }
}