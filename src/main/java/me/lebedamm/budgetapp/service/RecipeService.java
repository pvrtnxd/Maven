package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Recipe;

import java.util.Map;
import java.util.Optional;

public interface RecipeService {
    Recipe save (Recipe recipe);

    Optional<Recipe> getById(Long id);

    Recipe redacting(int id, Recipe recipe);

    Recipe delete (Long id);
    Recipe getInfo (Long id, Recipe recipe);
    Map<Integer, Recipe> getAll();

    Map<Integer, Recipe> pagination(Integer page, Integer limit);
}
