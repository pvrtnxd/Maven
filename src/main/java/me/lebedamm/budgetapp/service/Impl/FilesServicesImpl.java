package me.lebedamm.budgetapp.service.Impl;

import jakarta.annotation.PostConstruct;
import me.lebedamm.budgetapp.service.FilesServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServicesImpl implements FilesServices {
    @Value("${path.to.data.file}")
    private String dataFilesPath;
    @Value(value = "${name.of.recipe.data.file}")
    private String recipeDataFileName;

    @Value(value = "${name.of.ingredient.data.file}")
    private String ingredientDataFileName;

    public boolean saveRecipesFile(String json) {
        return saveToFile(json, recipeDataFileName);
    }

    @Override
    public String readRecipesFile() {
        return readFromFile(recipeDataFileName);
    }

    @Override
    public boolean saveIngredientsFile(String json) {
        return saveToFile(json, ingredientDataFileName);
    }

    @Override
    public String readIngredientsFile() {
        return readFromFile(ingredientDataFileName);
    }

    public boolean clearDataFile(String dataFileName) {
        Path path = Path.of(dataFilesPath, dataFileName);
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFile(String dataFileName) {
        try {
            return Files.readString(Path.of(dataFilesPath, dataFileName));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public boolean saveToFile(String json, String dataFileName) {
        try {
            clearDataFile(dataFileName);
            Files.writeString(Path.of(dataFilesPath, dataFileName), json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @PostConstruct
    private void init() {
        try {
            if (Files.notExists(Path.of(dataFilesPath, recipeDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, recipeDataFileName));
            }
            if (Files.notExists(Path.of(dataFilesPath, ingredientDataFileName))) {
                Files.createFile(Path.of(dataFilesPath, ingredientDataFileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
