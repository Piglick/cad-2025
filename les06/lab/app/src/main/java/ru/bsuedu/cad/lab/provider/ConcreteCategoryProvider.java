package ru.bsuedu.cad.lab.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.bsuedu.cad.lab.model.Category;
import ru.bsuedu.cad.lab.parser.CategoryCSVParser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class ConcreteCategoryProvider implements CategoryProvider {
    private final String fileName;
    private final CategoryCSVParser parser;

    public ConcreteCategoryProvider(
            @Value("${category.file.name}") String fileName,
            CategoryCSVParser parser
    ) {
        this.fileName = fileName;
        this.parser = parser;
    }

    @Override
    public List<Category> getCategories() {
        String data = readFile();
        return parser.parse(data);
    }

    private String readFile() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + fileName, e);
        }
    }
}