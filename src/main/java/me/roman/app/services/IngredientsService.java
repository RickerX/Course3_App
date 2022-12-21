package me.roman.app.services;

import me.roman.app.model.Ingredients;

public interface IngredientsService {
    Ingredients addIngredients(Ingredients ingredients);

    Ingredients getIngredients(String id);
}
