package me.roman.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class Recipes {
    private  String id;
    private final String name;
    private final int time;
    private final List<Ingredients> ingredients = new ArrayList<>();
    private final List<String> cookingSteps = new ArrayList<>();
}
