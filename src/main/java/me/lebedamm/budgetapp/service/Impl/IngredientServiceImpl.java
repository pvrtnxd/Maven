package me.lebedamm.budgetapp.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import me.lebedamm.budgetapp.exception.ValException;
import me.lebedamm.budgetapp.model.Ingredient;
import me.lebedamm.budgetapp.model.Recipe;
import me.lebedamm.budgetapp.service.FilesServices;
import me.lebedamm.budgetapp.service.IngredientService;
import me.lebedamm.budgetapp.service.ValService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Service
public class IngredientServiceImpl implements IngredientService {

    private final FilesServices filesServices;

    private static long idCounter = 1;
    private Map<Integer, Ingredient> ingredientMap = new HashMap<>();

    private final ValService valService;


    public IngredientServiceImpl(FilesServices filesServices, ValService valService) {
        this.filesServices = filesServices;
        this.valService = valService;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {

        if (!valService.validate(ingredient)) {

            throw new ValException(ingredient.toString());
        }
        return ingredientMap.put((int) idCounter++, ingredient);

    }

    @Override
    public Optional<Ingredient> getById(Long id) {
        return Optional.ofNullable(ingredientMap.get(id));
    }


    @Override
    public Ingredient redacting(Long id, Ingredient ingredient) {
        return ingredientMap.replace(Math.toIntExact(id), ingredient);
    }

    @Override
    public Ingredient delete(Long id) {
        return ingredientMap.remove(id);
    }

    @Override
    public Ingredient getInfo(Long id, Ingredient ingredient) {
        return ingredientMap.get(ingredient);
    }

    @Override
    public Map<Integer, Ingredient> getAll() {
        return ingredientMap;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesServices.saveIngredientsFile(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        String json = filesServices.readIngredientsFile();
        try {
            if (!json.isBlank()) {
                ingredientMap = new ObjectMapper().readValue(json, new TypeReference<Map<Integer, Ingredient>>() {
                });
                idCounter = ingredientMap.size();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
