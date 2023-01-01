package me.roman.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredients {
    private String id;
    private String name;
    private int numberOfIngredients;
    private String unitOfMeasurement;

}
