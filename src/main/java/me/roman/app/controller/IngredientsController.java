package me.roman.app.controller;

import me.roman.app.model.Ingredients;
import me.roman.app.services.impl.IngredientsServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/ingredients")
public class IngredientsController {
    public final IngredientsServiceImpl ingredientsService;

    public IngredientsController(IngredientsServiceImpl ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping("/get/ingredients")
    public Ingredients getIngredients() {
        return this.ingredientsService.getIngredients("1");
    }
    @GetMapping("/all/ingredients")
    public Collection<Ingredients> getAll() {
        return this.ingredientsService.getAll();
    }
    @PostMapping("/add/ingredients")
    public Ingredients addRecipes(@RequestBody Ingredients ingredients) {
        return this.ingredientsService.addIngredients(ingredients);
    }
}
