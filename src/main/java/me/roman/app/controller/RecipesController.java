package me.roman.app.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.model.Recipes;
import me.roman.app.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
@Tag(name = "Рецепты", description = "CRUD - операции")
public class RecipesController {
    private final RecipesServiceImpl recipesService;

    public RecipesController(RecipesServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Поиск рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Recipes getRecipesById(@PathVariable("id") String id) {
        return this.recipesService.getRecipesById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Поиск всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Collection<Recipes> getAll() {
        return this.recipesService.getAll();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавить рецепт")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты был добавлен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Recipes addRecipes(@RequestBody Recipes recipes) {
        return this.recipesService.addRecipes(recipes);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт был удален по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Recipes deleteRecipesById(@PathVariable("id") String id) {
        return this.recipesService.deleteRecipesById(id);
    }


    @PutMapping("/update/{id}")
    @Operation(summary = "Обновление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепты был обновлен по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Recipes updateRecipesById(@PathVariable String id, @RequestBody Recipes recipes) {
        return this.recipesService.updateRecipesById(id, recipes);
    }
}
