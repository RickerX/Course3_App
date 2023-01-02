package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.roman.app.model.Recipe;
import me.roman.app.services.RecipesService;
import me.roman.app.services.ServiceException;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    final private FilesServiceRecipesImpl filesServiceRecipes;
    private Map<String, Recipe> recipesMap = new HashMap<>();

    public RecipesServiceImpl(FilesServiceRecipesImpl filesServiceRecipes) {
        this.filesServiceRecipes = filesServiceRecipes;
    }

    public Collection<Recipe> getAll() {
        return recipesMap.values();
    }

    @Override
    public Recipe add(Recipe recipes) {
        if (recipesMap.containsKey(recipes.getId())) {
            try {
                throw new ServiceException("Нельзя добавить дубликат рецепта");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
        } else {
            recipesMap.put(recipes.getId(), recipes);
        }
        saveToFile();
        return recipes;
    }

    @Override
    public Recipe getById(String id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }

    @Override
    public Recipe deleteById(String id) {
        return recipesMap.remove(id);
    }

    @Override
    public Recipe updateById(String id, Recipe recipes) {
        Recipe serviceRecipes = recipesMap.get(id);
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
            throw new RuntimeException("не удалось сохранить json фаил");
        }
    }

    private void readFromFile() {
        String json = filesServiceRecipes.readFromFileRecipes();
        try {
            recipesMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Recipe>>() {
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
