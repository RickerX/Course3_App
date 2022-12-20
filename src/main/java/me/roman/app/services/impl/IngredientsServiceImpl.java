package me.roman.app.services.impl;
import me.roman.app.model.Ingredients;

import me.roman.app.services.IngredientsService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientsServiceImpl implements IngredientsService {
    private final Map<String, Ingredients> ingredientsMap = new HashMap<>();
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
        return ingredients;
    }

    @Override
    public Ingredients getIngredients(String id) {
        if (ingredientsMap.containsKey(id)) {
            return ingredientsMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }
}
