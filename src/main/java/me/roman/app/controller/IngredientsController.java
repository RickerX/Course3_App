package me.roman.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.model.Ingredient;
import me.roman.app.model.Recipe;
import me.roman.app.services.FilesServiceIngredients;
import me.roman.app.services.impl.IngredientsServiceImpl;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/ingredients")
@Tag(name = "Ингредиенты", description = "CRUD - операции")
public class IngredientsController {
    public final IngredientsServiceImpl ingredientsService;

    public IngredientsController(IngredientsServiceImpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Ingredient getIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.getById(id);
    }

    @GetMapping("/")
    @Operation(summary = "Поиск всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Collection<Ingredient> getAll() {
        return this.ingredientsService.getAll();
    }

    @PostMapping("/")
    @Operation(summary = "Добавление ингредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены добавлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Ingredient addIngredients(@RequestBody Ingredient ingredients) {
        return this.ingredientsService.add(ingredients);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были удалены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Ingredient deleteIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.deleteById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Обновление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были обновлены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                            )
                    }
            )
    })
    public Ingredient updateIngredientsById(@PathVariable String id, @RequestBody Ingredient ingredients) {
        return this.ingredientsService.updateById(id, ingredients);
    }
    @GetMapping("/report/{id}")
    public ResponseEntity<Object> getIngredientsReport(@PathVariable String id) {
        try {
            Path path = ingredientsService.createIngredientsReport(id);
            if (Files.size(path) == 0) {
                return ResponseEntity.noContent().build();
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(path.toFile()));
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(Files.size(path))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""
                            + id + "-report.txt\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    @PostMapping(value = "/import",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addIngredientsFromFile(@RequestParam MultipartFile file) {
        try (InputStream stream = file.getInputStream()){
            ingredientsService.addIngredientsFromInputStream(stream);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }
}
