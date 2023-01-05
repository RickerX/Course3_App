package me.roman.app.services.impl;

import me.roman.app.services.FilesServiceIngredients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceIngredientsImpl implements FilesServiceIngredients {
    @Value("${patn.of.ingredients.file}")
    private String dataFilePath;
    @Value("${name.of.ingredients.file}")
    private String dataFileName;
    @Override
    public boolean saveToFileIngredients(String json) {
        try {
            deleteDataFile();
            Files.writeString(Path.of(dataFilePath,dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFileIngredients() {
        try {
            return Files.readString(Path.of(dataFilePath,dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean deleteDataFile() {
        try {
            Path path = Path.of(dataFilePath,dataFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
