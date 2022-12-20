package me.roman.app.services;

import me.roman.app.model.Recipes;

public interface RecipesService {
    Recipes addRecipes(Recipes recipes);
    Recipes getRecipes(String id);
}
