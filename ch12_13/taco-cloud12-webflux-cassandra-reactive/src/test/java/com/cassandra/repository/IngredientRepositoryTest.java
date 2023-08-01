package com.cassandra.repository;

import com.cassandra.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@DataCassandraTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class IngredientRepositoryTest {

    private final IngredientRepository repo;

    @BeforeEach
    void setUp() {
        Flux<Ingredient> deleteAndInsert = repo
                .deleteAll()
                .thenMany(repo.saveAll(
                        Flux.just(
                                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                                new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE)
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
                            new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
                    assertThat(ingredients).contains(
                            new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
                    assertThat(ingredients).contains(
                            new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE));
                })
                .verifyComplete();

        StepVerifier.create(repo.findById("FLTO"))
                .assertNext(ingredient ->
                        ingredient.equals(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP)))
                .verifyComplete();
    }
}