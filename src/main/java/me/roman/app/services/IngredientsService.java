package me.roman.app.services;

import me.roman.app.model.Ingredient;

public interface IngredientsService {
    Ingredient add(Ingredient ingredients);

    Ingredient getById(String id);

    Ingredient deleteById(String id);


    Ingredient updateById(String id, Ingredient ingredients);
}
