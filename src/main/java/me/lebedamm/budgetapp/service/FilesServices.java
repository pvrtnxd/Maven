package me.lebedamm.budgetapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FilesServices {
    boolean saveRecipesFile(String json);

    String readRecipesFile();

    boolean saveIngredientsFile(String json);
    String readIngredientsFile();

    File getDataIngredientFile();

    File getDataRecipeFile();

    void importDataRecipeFile(MultipartFile file) throws IOException;

    void importDataIngredientFile(MultipartFile file) throws IOException;

    File getDataRecipeFileInfo();

    File getDataIngredientFileInfo();
}
