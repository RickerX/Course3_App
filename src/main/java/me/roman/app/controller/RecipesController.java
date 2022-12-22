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

    @GetMapping("/get/{id}")
    public Recipes getRecipesById(@PathVariable("id") String id) {
        return this.recipesService.getRecipesById(id);
    }

    @GetMapping("/all")
    public Collection<Recipes> getAll() {
        return this.recipesService.getAll();
    }

    @PostMapping("/add")
    public Recipes addRecipes(@RequestBody Recipes recipes) {
        return this.recipesService.addRecipes(recipes);
    }

    @DeleteMapping("/delete/{id}")
    public Recipes deleteRecipesById(@PathVariable("id") String id) {
        return this.recipesService.deleteRecipesById(id);
    }
    @DeleteMapping("/delete")
    public boolean deleteRecipes(Recipes recipes) {
        return this.recipesService.deleteRecipes(recipes);
    }

    @PutMapping("/update/{id}")
    public Recipes updateRecipesById(@PathVariable("id") String id, @RequestBody Recipes recipes) {
        return this.recipesService.updateRecipesById(id, recipes);
    }
}
