package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Ingredient;

import java.util.Map;
import java.util.Optional;

public interface IngredientService {
    Ingredient save(Ingredient ingredient);

    Optional<Ingredient> getById(Long id);

    Ingredient redacting(Long id, Ingredient ingredient);

    Ingredient delete(Long id);

    Ingredient getInfo(Long id, Ingredient ingredient);

    Map<Integer, Ingredient> getAll();
}

