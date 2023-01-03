package me.roman.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.services.FilesServiceRecipes;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/files/recipes")
@Tag(name = "Файлы с рецептами", description = "Загрузка/Выгрузка файлов")
public class FilesControllerRecipe {
    private final FilesServiceRecipes filesServiceRecipes;

    public FilesControllerRecipe(FilesServiceRecipes filesServiceRecipes) {
        this.filesServiceRecipes = filesServiceRecipes;
    }

    @GetMapping("/export")
    @Operation(summary = "Выгрузка файлов с рецептами")
    public ResponseEntity<InputStreamResource> downloadDataFileRecipes() throws FileNotFoundException {
        File exportDataFileRecipes = filesServiceRecipes.getDataFileRecipes();
        if (exportDataFileRecipes.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(exportDataFileRecipes));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(exportDataFileRecipes.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"RecipesLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_MIXED_VALUE)
    @Operation(summary = "Загрузка файлов с рецептами")
    public ResponseEntity<Void> uploadDataFileRecipes(@RequestParam MultipartFile multipartFile) {
        filesServiceRecipes.deleteDataFileRecipes();
        File importDataFileRecipes = filesServiceRecipes.getDataFileRecipes();
        try (FileOutputStream fos = new FileOutputStream(importDataFileRecipes)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
