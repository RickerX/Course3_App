package me.roman.app.services.impl;

import me.roman.app.services.FilesServiceRecipes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceRecipesImpl implements FilesServiceRecipes {
    @Value("${patn.of.recipes.file}")
    private String dataFilePath;
    @Value("${name.of.recipes.file}")
    private String dataFileName;
    @Override
    public boolean saveToFileRecipes(String json) {
        try {
            deleteDataFile();
            Files.writeString(Path.of(dataFilePath,dataFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @Override
    public String readFromFileRecipes() {
        try {
            return Files.readString(Path.of(dataFilePath,dataFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public boolean deleteDataFile() {
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
