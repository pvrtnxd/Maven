package me.lebedamm.budgetapp.service.Impl;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.ValService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class ValServiceImpl implements ValService {


    @Override
    public boolean validate(Recipe recipe) {
        return recipe != null
                && recipe.getName() != null
                && !StringUtils.isEmpty(recipe.getName())
                && recipe.getSteps() != null
                && recipe.getIngredients() != null
                && !recipe.getIngredients().isEmpty()
                && !recipe.getSteps().isEmpty();
    }

    @Override
    public boolean validate(Ingredient ingredient) {
        return ingredient != null
                && ingredient.getName() != null
                && !StringUtils.isEmpty(ingredient.getName());

    }
}
