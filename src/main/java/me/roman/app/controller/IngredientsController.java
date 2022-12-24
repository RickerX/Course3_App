package me.roman.app.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.roman.app.model.Ingredients;
import me.roman.app.model.Recipes;
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

    @GetMapping("/get/{id}")
    @Operation(summary = "Поиск ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Ingredients getIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.getIngredientsById(id);
    }

    @GetMapping("/all")
    @Operation(summary = "Поиск всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Collection<Ingredients> getAll() {
        return this.ingredientsService.getAll();
    }

    @PostMapping("/add")
    @Operation(summary = "Добавление ингредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были найдены добавлены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Ingredients addRecipes(@RequestBody Ingredients ingredients) {
        return this.ingredientsService.addIngredients(ingredients);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Удаление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были удалены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Ingredients deleteIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.deleteIngredientsById(id);
    }



    @PutMapping("/update/{id}")
    @Operation(summary = "Обновление ингредиента по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ингредиенты были обновлены по id",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recipes.class))
                            )
                    }
            )
    })
    public Ingredients updateIngredientsById(@PathVariable String id, @RequestBody Ingredients ingredients) {
        return this.ingredientsService.updateIngredientsById(id, ingredients);
    }
}
