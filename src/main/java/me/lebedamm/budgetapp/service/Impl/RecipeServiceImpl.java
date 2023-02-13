package me.lebedamm.budgetapp.service.Impl;

import me.lebedamm.budgetapp.exception.ValException;
import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.RecipeService;
import me.lebedamm.budgetapp.service.ValService;
import org.springframework.stereotype.Service;

import java.rmi.server.RemoteRef;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class RecipeServiceImpl implements RecipeService {

    private static long idCounter = 1;
    private final Map<Long, Recipe> recipeMap = new HashMap<Long, Recipe>();

    private final ValService valService;

    public RecipeServiceImpl(ValService valService) {
        this.valService = valService;
    }


    @Override
    public Recipe save(Recipe recipe) {
        if (!valService.validate(recipe)) {

            throw new ValException(recipe.toString());
        }
        return recipeMap.put(idCounter++, recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }


    @Override
    public Recipe redacting(Long id, Recipe recipe) {
        return recipeMap.replace(id, recipe);
    }

    @Override
    public Recipe delete(Long id) {
        return recipeMap.remove(id);
    }

    @Override
    public Recipe getInfo(Long id, Recipe recipe) {
        return recipeMap.get(recipe);
    }

    @Override
    public Map<Long, Recipe> getAll() {
        return recipeMap;
    }
}
