package me.lebedamm.budgetapp.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.lebedamm.budgetapp.exception.ValException;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.FilesServices;
import me.lebedamm.budgetapp.service.RecipeService;
import me.lebedamm.budgetapp.service.ValService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class RecipeServiceImpl implements RecipeService {

    private final FilesServices filesServices;

    private static int idCounter = 1;
    private static Map<Integer, Recipe> recipeMap = new HashMap<>();

    private final ValService valService;

    public RecipeServiceImpl(FilesServices filesServices, ValService valService) {
        this.filesServices = filesServices;
        this.valService = valService;
    }


    @Override
    public Recipe save(Recipe recipe) {
        if (!valService.validate(recipe)) {

            throw new ValException(recipe.toString());
        }
        return recipeMap.put( idCounter++, recipe);
    }

    @Override
    public Optional<Recipe> getById(Long id) {
        return Optional.ofNullable(recipeMap.get(id));
    }


    @Override
    public Recipe redacting(int id, Recipe recipe) {
        if (recipeMap.containsKey(id)) {
            recipeMap.put(id, recipe);
            saveToFile();
            return recipe;
        }
        return null;
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
    public Map<Integer, Recipe> getAll() {
        return recipeMap;
    }

    @Override
    public Map<Integer, Recipe> pagination(Integer page, Integer limit) {
        return null;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesServices.saveIngredientsFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesServices.readIngredientsFile();
        try {
            if (!json.isBlank()) {
                recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Recipe>>() {
                });
                idCounter = recipeMap.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
