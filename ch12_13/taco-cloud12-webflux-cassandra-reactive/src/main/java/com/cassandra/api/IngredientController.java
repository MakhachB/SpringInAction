package com.cassandra.api;

import com.cassandra.model.Ingredient;
import com.cassandra.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:3000", "http://tacocloudTest.com"})
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepository repo;

    @GetMapping
    public Flux<Ingredient> allIngredients() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Ingredient> byId(@PathVariable String id) {
        return repo.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Ingredient> saveIngredient(@RequestBody Mono<Ingredient> ingredient) {
        return repo.saveAll(ingredient).next();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable String id) {
        repo.findById(id)
                .doOnNext(repo::delete)
                .subscribe();
    }

}

//curl --location "http://localhost:8080/api/ingredients" --header "Content-Type: application/json" -u "test2:12345" --data "{\"id\": \"КУРЦ\",\"name\": \"Курица\",\"type\": \"PROTEIN\"}"