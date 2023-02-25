package me.lebedamm.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/recipe")
@Validated
@Tag(name = "Api рецептов",
      description = "CRUD рецептов. Поиск по ингредиентам ")
@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Получен результат запроса"),
        @ApiResponse(responseCode = "400", description = "Некорректный запрос серверу"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
})
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @Operation(summary = "Сохранение рецепта")
    @PostMapping
    public ResponseEntity<Recipe> save(@RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.save(recipe));
    }

    @Operation(summary = "Данные рецепта по id")
    @GetMapping ("/{id}")
    public ResponseEntity<Recipe> getById(@PathVariable Long id) {
        return ResponseEntity.of(recipeService.getById(id));
    }


    @Operation(summary = "Редактирование рецепта")
    @PutMapping("/{id}")
    public ResponseEntity<Recipe> redacting (@PathVariable Long id, @RequestBody Recipe recipe) {
        return ResponseEntity.ok(recipeService.redacting(id, recipe));
    }

    @Operation(summary = "Удаление рецепта")
    @DeleteMapping("/{id}")
    public ResponseEntity<Recipe> delete (@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.delete(id));
    }

    @Operation(summary = "Список всех рецептов")
    @GetMapping
    public ResponseEntity<Map<Integer, Recipe>> getAllRecipes(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "0") Integer limit) {
        Map<Integer, Recipe> recipes = recipeService.pagination(page, limit);
        return ResponseEntity.ok(recipes);
    }
}
