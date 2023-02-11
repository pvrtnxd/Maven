package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.exception.ValException;
import me.lebedamm.budgetapp.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class IngredientServiceImpl implements IngredientService {

    private static long idCounter = 1;
    private final Map<Long, Ingredient> ingredientMap = new HashMap<>();

    private final ValService valService;

    public IngredientServiceImpl(ValService valService) {
        this.valService = valService;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if (valService.validate(ingredient)) {

            throw new ValException(ingredient.toString());
        }
        return ingredientMap.put(idCounter++, ingredient);

    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }
}