package me.roman.app.services.impl;

import me.roman.app.services.FilesServiceIngredients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
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
            deleteDataFileIngredients();
            Files.writeString(Path.of(dataFilePath,dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public Path createTempFileIngredients() {
        try {
            return Files.createTempFile(Path.of(dataFilePath),"tempFile","suffix");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    @Override
    public boolean deleteDataFileIngredients() {
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
    @Override
    public File getDataFileIngredients() {
        return new File(dataFilePath + "/" + dataFileName);
    }
}
