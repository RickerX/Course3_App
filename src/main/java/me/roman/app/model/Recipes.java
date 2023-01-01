package me.roman.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipes {
    private  String id;
    private  String name;
    private  int time;
    private final List<Ingredients> ingredients = new ArrayList<>();
    private final List<String> cookingSteps = new ArrayList<>();
}
