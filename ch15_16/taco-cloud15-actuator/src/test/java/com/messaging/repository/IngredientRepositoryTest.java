package com.messaging.repository;

import com.messaging.model.Ingredient;
import com.messaging.model.Ingredient.Type;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class IngredientRepositoryTest {

    private final IngredientRepository repo;

    @BeforeEach
    void setUp() {
        Flux<Ingredient> deleteAndInsert = repo
                .deleteAll()
                .thenMany(repo.saveAll(
                        Flux.just(
                                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                                new Ingredient("CHED", "Cheddar Cheese", Type.CHEESE)
                        )));

        StepVerifier.create(deleteAndInsert)
                .expectNextCount(3)
                .verifyComplete();
    }

    @Test
    public void shouldSaveAndFetchIngredients() {
        StepVerifier.create(repo.findAll())
                .recordWith(ArrayList::new)
                .thenConsumeWhile(x -> true)
                .consumeRecordedWith(ingredients -> {
                    assertThat(ingredients).hasSize(3);
                    assertThat(ingredients).contains(
                            new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
                    assertThat(ingredients).contains(
                            new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
                    assertThat(ingredients).contains(
                            new Ingredient("CHED", "Cheddar Cheese", Type.CHEESE));
                })
                .verifyComplete();

        StepVerifier.create(repo.findById("FLTO"))
                .assertNext(ingredient ->
                        ingredient.equals(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP)))
                .verifyComplete();
    }
}