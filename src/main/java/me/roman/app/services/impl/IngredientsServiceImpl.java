package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.roman.app.model.Ingredient;

import me.roman.app.services.IngredientsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@Qualifier
public class IngredientsServiceImpl implements IngredientsService {
    final private FilesServiceIngredientsImpl filesServiceIngredients;
    private Map<String, Ingredient> ingredientsMap = new HashMap<>();

    public IngredientsServiceImpl(FilesServiceIngredientsImpl filesServiceIngredients) {
        this.filesServiceIngredients = filesServiceIngredients;
    }

    public Collection<Ingredient> getAll() {
        return ingredientsMap.values();
    }

    @Override
    public Ingredient add(Ingredient ingredients) {
        if (ingredientsMap.containsKey(ingredients.getId())) {
            throw new RuntimeException("Нельзя добавить дубликат рецепта");
        } else {
            ingredientsMap.put(ingredients.getId(), ingredients);
        }
        saveToFile();
        return ingredients;
    }

    @Override
    public Ingredient getById(String id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }

    @Override
    public Ingredient deleteById(String id) {
        return ingredientsMap.remove(id);
    }



    @Override
    public Ingredient updateById(String id, Ingredient ingredients) {
        Ingredient serviceIngredients = ingredientsMap.get(id);
        if (serviceIngredients == null) {
            throw new RuntimeException("Ингридиент не найден");
        }
        ingredientsMap.replace(id, ingredients);
        saveToFile();
        return ingredients;
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientsMap);
            filesServiceIngredients.saveToFileIngredients(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesServiceIngredients.readFromFileIngredients();
        try {
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}
