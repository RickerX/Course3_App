package me.roman.app.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.model.Recipe;
import me.roman.app.services.RecipesService;
import me.roman.app.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD - операции")
public class RecipesController implements RecipesService {
    private final RecipesServiceImpl recipesService;

    public RecipesController(RecipesServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe getRecipesById(@PathVariable("id") String id) {
        return this.recipesService.getById(id);
    }

    @GetMapping("/")
    @Operation(summary = "Поиск всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Collection<Recipe> getAll() {
        return this.recipesService.getAll();
    }

    @PostMapping("/")
    @Operation(summary = "Добавить рецепт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты был добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe addRecipes(@RequestBody Recipe recipes) {
        return this.recipesService.add(recipes);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был удален по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe deleteRecipesById(@PathVariable("id") String id) {
        return this.recipesService.deleteById(id);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Обновление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты был обновлен по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Recipe updateRecipesById(@PathVariable String id, @RequestBody Recipe recipes) {
        return this.recipesService.updateById(id, recipes);
    }

    @Override
    public Recipe add(Recipe recipes) {
        return null;
    }

    @Override
    public Recipe getById(String id) {
        return null;
    }

    @Override
    public Recipe deleteById(String id) {
        return null;
    }

    @Override
    public Recipe updateById(String id, Recipe recipes) {
        return null;
    }
}
