package me.lebedamm.budgetapp.controllers;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recipe> redacting (@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.redacting(id, recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete (@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }
    @GetMapping
    public ResponseEntity<Map<Long, Recipe>> getAll() {
        return ResponseEntity.ok(recipeService.getAll());
    }
}
