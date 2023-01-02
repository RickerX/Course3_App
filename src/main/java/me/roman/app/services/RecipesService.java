package me.roman.app.services;

import me.roman.app.model.Recipe;

public interface RecipesService {
    Recipe add(Recipe recipes);
    Recipe getById(String id);
    Recipe deleteById(String id);
    Recipe updateById(String id, Recipe recipes);
}
