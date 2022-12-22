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

    @GetMapping("/get/{id}")
    public Ingredients getIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.getIngredientsById(id);
    }

    @GetMapping("/all")
    public Collection<Ingredients> getAll() {
        return this.ingredientsService.getAll();
    }

    @PostMapping("/add")
    public Ingredients addRecipes(@RequestBody Ingredients ingredients) {
        return this.ingredientsService.addIngredients(ingredients);
    }

    @DeleteMapping("/delete/{id}")
    public Ingredients deleteIngredientsById(@PathVariable("id") String id) {
        return this.ingredientsService.deleteIngredientsById(id);
    }

    @DeleteMapping("/delete")
    public Ingredients deleteIngredients(Ingredients ingredients) {
        return this.ingredientsService.deleteIngredients(ingredients);
    }

    @PostMapping("/update/{id}")
    public Ingredients updateIngredientsById(@PathVariable("id") String id, @RequestBody Ingredients ingredients) {
        return this.ingredientsService.updateIngredientsById(id, ingredients);
    }
}
