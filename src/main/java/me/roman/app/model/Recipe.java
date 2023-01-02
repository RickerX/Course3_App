package me.roman.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private  String id;
    private  String name;
    private  int time;
    private final List<Ingredient> ingredients = new ArrayList<>();
    private final List<String> cookingSteps = new ArrayList<>();
}
