package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Ingredient;

import java.util.Optional;

public interface IngredientService {
    Ingredient save (Ingredient ingredient);

    Optional<Ingredient> getById(Long id);
}
