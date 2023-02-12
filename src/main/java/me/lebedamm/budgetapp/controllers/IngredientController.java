package me.lebedamm.budgetapp.controllers;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @PutMapping("/add")
    public ResponseEntity<Ingredient> add (@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.add(id, ingredient));
    }
    @PutMapping("/redact {id}")
    public ResponseEntity<Ingredient> redacting (@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.redacting(id, ingredient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete (@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }
    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}

