package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Ingredient;

import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public interface IngredientService {
    Ingredient save (Ingredient ingredient);

    Optional<Ingredient> getById(Long id);

    Ingredient add (Long id, Ingredient ingredient);
    Ingredient redacting (Long id, Ingredient ingredient);
    Ingredient delete (Long id);
    Ingredient getInfo (Long id, Ingredient ingredient);
    Map<Long, Ingredient> getAll();

}
