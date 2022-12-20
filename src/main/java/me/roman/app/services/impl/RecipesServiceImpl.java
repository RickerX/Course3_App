package me.roman.app.services.impl;
import me.roman.app.model.Recipes;
import me.roman.app.services.RecipesService;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class RecipesServiceImpl implements RecipesService {
    private final Map<String, Recipes> recipesMap = new HashMap<>();

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
        return recipes;
    }

    @Override
    public Recipes getRecipes(String id) {
        if (recipesMap.containsKey(id)) {
            return recipesMap.get(id);
        } else {
            throw new RuntimeException("Нет такого рецепта");
        }
    }
}
