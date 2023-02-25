package me.lebedamm.budgetapp.service;

import java.io.IOException;

public interface FilesServices {
    boolean saveRecipesFile(String json);

    String readRecipesFile();

    boolean saveIngredientsFile(String json);
    String readIngredientsFile();
}
