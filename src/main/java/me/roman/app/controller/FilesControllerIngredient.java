package me.roman.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.services.FilesServiceIngredients;
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
@RequestMapping("/files/ingredient")
@Tag(name = "Файлы с ингредиентами", description = "Загрузка/Выгрузка файлов")
public class FilesControllerIngredient {
    private final FilesServiceIngredients filesServiceIngredients;

    public FilesControllerIngredient(FilesServiceIngredients filesServiceIngredients) {
        this.filesServiceIngredients = filesServiceIngredients;
    }

    @GetMapping("/export")
    @Operation(summary = "Выгрузка файлов с ингредиентами")
    public ResponseEntity<InputStreamResource> downloadDataFileIngredients() throws FileNotFoundException {
        File dataFileIngredients = filesServiceIngredients.getDataFileIngredients();
        if (dataFileIngredients.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(dataFileIngredients));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(dataFileIngredients.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"IngredientsLog.json\"")
                    .body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_MIXED_VALUE)
    @Operation(summary = "Загрузка файлов с ингредиентами")
    public ResponseEntity<Void> uploadDataFileIngredients(@RequestParam MultipartFile multipartFile) {
        filesServiceIngredients.deleteDataFileIngredients();
        File importDataFileIngredients = filesServiceIngredients.getDataFileIngredients();
        try (FileOutputStream fos = new FileOutputStream(importDataFileIngredients)) {
            IOUtils.copy(multipartFile.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
