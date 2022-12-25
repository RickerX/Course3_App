package me.roman.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ingredients {
    private String id;
    private final String name;
    private final int numberOfIngredients;
    private final String unitOfMeasurement;

}
