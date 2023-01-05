package me.roman.app.services;

public interface FilesServiceRecipes {
    boolean saveToFileRecipes(String json);

    String readFromFileRecipes();

    boolean deleteDataFile();
}
