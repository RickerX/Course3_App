package me.roman.app.services;

import me.roman.app.model.Ingredient;

import java.io.IOException;
import java.nio.file.Path;

public interface IngredientsService {
    Ingredient add(Ingredient ingredients);

    Ingredient getById(String id);

    Ingredient deleteById(String id);


    Ingredient updateById(String id, Ingredient ingredients);

    Path createIngredientsReport(String id) throws IOException;
}
