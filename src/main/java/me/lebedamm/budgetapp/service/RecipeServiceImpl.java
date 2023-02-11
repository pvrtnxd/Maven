package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.exception.ValException;
import me.lebedamm.budgetapp.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class RecipeServiceImpl implements RecipeService{

    private static long idCounter = 1;
    private final Map<Long, Recipe> recipeMap = new HashMap<Long, Recipe>();

    private final ValService valService;

    public RecipeServiceImpl(ValService valService) {
        this.valService = valService;
    }


    @Override
    public Recipe save(Recipe recipe) {
        if (valService.validate(recipe)) {

            throw new ValException(recipe.toString());
        }
        return recipeMap.put(idCounter++, recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }
}
