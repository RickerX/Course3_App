package me.roman.app.controller;

import me.roman.app.model.Recipes;
import me.roman.app.services.impl.RecipesServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipes")
public class RecipesController {
    private final RecipesServiceImpl recipesService;

    public RecipesController(RecipesServiceImpl recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping("/get")
    public Recipes getRecipes() {
        return this.recipesService.getRecipes("1");
    }
    @GetMapping("/all")
    public Collection<Recipes> getAll() {
        return this.recipesService.getAll();
    }
    @PostMapping("/add")
    public Recipes addRecipes(@RequestBody Recipes recipes) {
        return this.recipesService.addRecipes(recipes);
    }
}
