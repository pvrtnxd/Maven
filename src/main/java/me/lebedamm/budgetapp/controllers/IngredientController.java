package me.lebedamm.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Validated
@Tag(name = "Api ингредиентов",
        description = "CRUD ингредиентов")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Получен результат запроса"),
        @ApiResponse(responseCode = "400", description = "Некорректный запрос серверу"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    @Operation(summary = "Сохранение ингредиента")
    @PostMapping
    public ResponseEntity<Ingredient> save (@RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.save(ingredient));
    }

    @Operation(summary = "Данные ингредиента по id")
    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getById (@PathVariable Long id) {
        return ResponseEntity.of(ingredientService.getById(id));
    }

    @Operation(summary = "Редактирование ингредиента")
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> redacting (@PathVariable Long id, @RequestBody Ingredient ingredient) {
        return ResponseEntity.ok(ingredientService.redacting(id, ingredient));
    }

    @Operation(summary = "Удаление ингредиента")
    @DeleteMapping("/{id}")
    public ResponseEntity<Ingredient> delete (@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.delete(id));
    }
    @Operation(summary = "Получение списка всех ингредиентов")
    @GetMapping
    public ResponseEntity<Map<Long, Ingredient>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }
}

