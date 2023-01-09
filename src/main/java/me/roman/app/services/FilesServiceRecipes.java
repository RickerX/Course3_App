package me.roman.app.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesServiceRecipes {
    boolean saveToFileRecipes(String json);

    Path createTempFileRecipes();

    String readFromFileRecipes();

    boolean deleteDataFileRecipes();

    File getDataFileRecipes();
}
