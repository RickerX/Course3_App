package me.roman.app.services;

import me.roman.app.model.Recipes;

public interface RecipesService {
    Recipes addRecipes(Recipes recipes);
    Recipes getRecipesById(String id);
    Recipes deleteRecipesById(String id);
    Recipes updateRecipesById(String id, Recipes recipes);
}
