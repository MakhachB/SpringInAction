package com.messaging.api;

import com.messaging.model.Ingredient;
import com.messaging.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "http://tacocloudTest.com"})
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository repo;

    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repo.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable("id") String ingredientId) {
        repo.deleteById(ingredientId);
    }

}

//curl --location "http://localhost:8080/api/ingredients" --header "Content-Type: application/json" -u "test2:12345" --data "{\"id\": \"КУРЦ\",\"name\": \"Курица\",\"type\": \"PROTEIN\"}"