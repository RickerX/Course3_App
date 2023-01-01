package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.roman.app.model.Ingredients;

import me.roman.app.services.IngredientsService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    final private FilesServiceIngredientsImpl filesServiceIngredients;
    private Map<String, Ingredients> ingredientsMap = new HashMap<>();

    public IngredientsServiceImpl(FilesServiceIngredientsImpl filesServiceIngredients) {
        this.filesServiceIngredients = filesServiceIngredients;
    }

    public Collection<Ingredients> getAll() {
        return ingredientsMap.values();
    }

    @Override
    public Ingredients addIngredients(Ingredients ingredients) {
        if (ingredientsMap.containsKey(ingredients.getId())) {
            throw new RuntimeException("Нельзя добавить дубликат рецепта");
        } else {
            ingredientsMap.put(ingredients.getId(), ingredients);
        }
        saveToFile();
        return ingredients;
    }

    @Override
    public Ingredients getIngredientsById(String id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }

    @Override
    public Ingredients deleteIngredientsById(String id) {
        return ingredientsMap.remove(id);
    }



    @Override
    public Ingredients updateIngredientsById(String id, Ingredients ingredients) {
        Ingredients serviceIngredients = ingredientsMap.get(id);
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
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Ingredients>>() {
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
