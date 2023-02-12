package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeService {
    Recipe save (Recipe recipe);

    Optional<Recipe> getById(Long id);

    Recipe add (Long id, Recipe recipe);
    Recipe redacting (Long id, Recipe recipe);
    Recipe delete (Long id);
    Recipe getInfo (Long id, Recipe recipe);
    Map<Long, Recipe> getAll();
}
