package me.lebedamm.budgetapp.service;

import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;

public interface ValService {

    public boolean validate (Recipe recipe);
    public boolean validate (Ingredient ingredient);
}
