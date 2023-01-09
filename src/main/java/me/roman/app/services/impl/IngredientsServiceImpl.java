package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import me.roman.app.model.Ingredient;
import me.roman.app.services.IngredientsService;
import me.roman.app.services.ServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class IngredientsServiceImpl implements IngredientsService {
    private final FilesServiceIngredientsImpl filesServiceIngredients;
    private Map<String, Ingredient> ingredientsMap = new HashMap<>();

    public IngredientsServiceImpl(@Qualifier("filesServiceIngredientsImpl")
                                  FilesServiceIngredientsImpl filesServiceIngredients) {
        this.filesServiceIngredients = filesServiceIngredients;
    }

    public Collection<Ingredient> getAll() {
        return ingredientsMap.values();
    }

    @SneakyThrows
    @Override
    public Ingredient add(Ingredient ingredients) {
        if (ingredientsMap.containsKey(ingredients.getId())) {
            throw new ServiceException("Нельзя добавить дубликат рецепта");
        } else {
            ingredientsMap.put(ingredients.getId(), ingredients);
        }
        saveToFile();
        return ingredients;
    }

    @SneakyThrows
    @Override
    public Ingredient getById(String id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new ServiceException("Нет такого рецепта");
        }
    }

    @Override
    public Ingredient deleteById(String id) {
        return ingredientsMap.remove(id);
    }


    @SneakyThrows
    @Override
    public Ingredient updateById(String id, Ingredient ingredients) {
        Ingredient serviceIngredients = ingredientsMap.get(id);
        if (serviceIngredients == null) {
            throw new ServiceException("Ингридиент не найден");
        }
        ingredientsMap.replace(id, ingredients);
        saveToFile();
        return ingredients;
    }

    @SneakyThrows
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesServiceIngredients.saveToFileIngredients(json);
        } catch (JsonProcessingException e) {
            throw new ServiceException("Не удалось сохранить фаил");
        }
    }

    @SneakyThrows
    private void readFromFile() {
        String json = filesServiceIngredients.readFromFileIngredients();
        try {
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new ServiceException("Не удалось прочитать фаил");
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Path createIngredientsReport(String id) throws IOException {
        Path path = filesServiceIngredients.createTempFileIngredients("ingredientsReport");
        for (Ingredient ingredient : ingredientsMap.values()) {
            try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                writer.append(ingredient.getId() + "|" + ingredient.getName());
                writer.append("\n");
            }
        }
        return path;
    }
    @Override
    public void addIngredientsFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Ingredient ingredient = new Ingredient();
                add(ingredient);
            }
        }
    }
}
