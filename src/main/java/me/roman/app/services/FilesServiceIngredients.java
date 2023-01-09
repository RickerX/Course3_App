package me.roman.app.services;

import java.io.File;
import java.nio.file.Path;

public interface FilesServiceIngredients {
    boolean saveToFileIngredients(String json);
    Path createTempFileIngredients(String suffix);

    String readFromFileIngredients();

    boolean deleteDataFileIngredients();

    File getDataFileIngredients();
}
