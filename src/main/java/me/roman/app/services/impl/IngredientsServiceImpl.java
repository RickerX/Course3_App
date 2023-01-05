package me.roman.app.services.impl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.roman.app.model.Ingredient;
import me.roman.app.services.IngredientsService;
import me.roman.app.services.ServiceException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service

public class IngredientsServiceImpl implements IngredientsService {
    final private FilesServiceIngredientsImpl filesServiceIngredients;
    private Map<String, Ingredient> ingredientsMap = new HashMap<>();

    public IngredientsServiceImpl(@Qualifier("filesServiceIngredientsImpl")
                                  FilesServiceIngredientsImpl filesServiceIngredients) {
        this.filesServiceIngredients = filesServiceIngredients;
    }

    public Collection<Ingredient> getAll() {
        return ingredientsMap.values();
    }

    @Override
    public Ingredient add(Ingredient ingredients) {
        if (ingredientsMap.containsKey(ingredients.getId())) {
            try {
                throw new ServiceException("Нельзя добавить дубликат рецепта");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
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
            try {
                throw new ServiceException("Нет такого рецепта");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
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
            try {
                throw new ServiceException("Ингридиент не найден");
            } catch (ServiceException e) {
                throw new RuntimeException(e);
            }
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
            try {
                throw new ServiceException("Не удалось сохранить фаил");
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void readFromFile() {
        String json = filesServiceIngredients.readFromFileIngredients();
        try {
            ingredientsMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<String, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            try {
                throw new ServiceException("Не удалось прочитать фаил");
            } catch (ServiceException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }
}
