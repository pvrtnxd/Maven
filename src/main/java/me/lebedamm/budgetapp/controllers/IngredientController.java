package me.lebedamm.budgetapp.controllers;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @PostMapping
    public ResponseEntity<Ingredient> save (@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById (@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }
}

