package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.roman.app.model.Recipes;
import me.roman.app.services.RecipesService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    final private FilesServiceRecipesImpl filesServiceRecipes;
    private Map<String, Recipes> recipesMap = new HashMap<>();

    public RecipesServiceImpl(FilesServiceRecipesImpl filesServiceRecipes) {
        this.filesServiceRecipes = filesServiceRecipes;
    }

    public Collection<Recipes> getAll() {
        return recipesMap.values();
    }

    @Override
    public Recipes addRecipes(Recipes recipes) {
        if (recipesMap.containsKey(recipes.getId())) {
            throw new RuntimeException("Нельзя добавить дубликат рецепта");
        } else {
            recipesMap.put(recipes.getId(), recipes);
        }
        saveToFile();
        return recipes;
    }

    @Override
    public Recipes getRecipesById(String id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }

    @Override
    public Recipes deleteRecipesById(String id) {
        return recipesMap.remove(id);
    }

    @Override
    public Recipes updateRecipesById(String id, Recipes recipes) {
        Recipes serviceRecipes = recipesMap.get(id);
        if (serviceRecipes == null) {
            throw new RuntimeException("Рецепт не найден");
        }
        recipesMap.replace(id, recipes);
        saveToFile();
        return recipes;
    }
    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipesMap);
            filesServiceRecipes.saveToFileRecipes(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readFromFile() {
        String json = filesServiceRecipes.readFromFileRecipes();
        try {
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Recipes>>() {
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
