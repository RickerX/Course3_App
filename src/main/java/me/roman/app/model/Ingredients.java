package me.roman.app.model;

public class Ingredients {
    private final String id;
    private final String name;
    private final int numberOfIngredients;
    private final String unitOfMeasurement;

    public Ingredients(String id, String name, int numberOfIngredients, String unitOfMeasurement) {
        this.id = id;
        this.name = name;
        this.numberOfIngredients = numberOfIngredients;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfIngredients() {
        return numberOfIngredients;
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public String getId() {
        return id;
    }
}
