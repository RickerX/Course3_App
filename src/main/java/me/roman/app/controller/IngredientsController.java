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
import me.roman.app.services.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
                                    array = @ArraySchema(schema = @Schema(implementation = Recipe.class))
                            )
                    }
            )
    })
    public Ingredient updateIngredientsById(@PathVariable String id, @RequestBody Ingredient ingredients) {
        return this.ingredientsService.updateById(id, ingredients);
    }
}
