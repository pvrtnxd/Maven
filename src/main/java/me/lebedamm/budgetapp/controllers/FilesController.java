package me.lebedamm.budgetapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import me.lebedamm.budgetapp.service.FilesServices;
import me.lebedamm.budgetapp.service.RecipeService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@RestController
@RequestMapping("/files")
public class FilesController {

    private final FilesServices filesServices;


    public FilesController(FilesServices filesServices) {
        this.filesServices = filesServices;
    }
    @GetMapping("/export")
    @Operation( summary = "Экспорт рецепта")
    @ApiResponse( responseCode = "200",
    description = "Рецепты загружены")

    public ResponseEntity<InputStreamResource> downloadDataRecipeFile() throws FileNotFoundException {
        filesServices.getDataIngredientFile();
        if (filesServices.getDataIngredientFile().exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(filesServices.getDataIngredientFile()));
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).
                    contentLength(filesServices.getDataIngredientFile().length()).
                    header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename = \"Recipes.json\"").
                    body(resource);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @Operation (summary = "Импорт рецептов")
    @ApiResponse ( responseCode = "200",
            description = "Рецепты загружены")
    @PostMapping (value = "import/recipes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importDataRecipeFile(@RequestParam MultipartFile file) throws IOException {
        filesServices.importDataRecipeFile(file);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Импорт ингредиентов")
    @ApiResponse(responseCode = "200",
            description = "Ингредиенты загружены"
    )
    @PostMapping(value = "import/ingredients", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importDataIngredientFile(@RequestParam MultipartFile file) throws IOException {
        filesServices.importDataIngredientFile(file);
        return ResponseEntity.ok().build();
    }
}



