package me.roman.app.model;

import java.util.ArrayList;
import java.util.List;

public class Recipes {
    private  String id;
    private final String name;
    private final int time;
    private final List<Ingredients> ingredients = new ArrayList<>();
    private final List<String> cookingSteps = new ArrayList<>();

    public Recipes(String id, String name, int time) {
        this.id = id;
        this.name = name;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTime() {
        return time;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<String> getCookingSteps() {
        return cookingSteps;
    }

    public void setId(String id) {
        this.id = id;
    }
}
