package me.roman.app.services;

import me.roman.app.model.Ingredients;

public interface IngredientsService {
    Ingredients addIngredients(Ingredients ingredients);

    Ingredients getIngredientsById(String id);

    Ingredients deleteIngredientsById(String id);


    Ingredients updateIngredientsById(String id, Ingredients ingredients);
}
