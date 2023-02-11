package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Recipe;

import java.util.Optional;

public interface RecipeService {
    Recipe save (Recipe recipe);

    Optional<Recipe> getById(Long id);
}
