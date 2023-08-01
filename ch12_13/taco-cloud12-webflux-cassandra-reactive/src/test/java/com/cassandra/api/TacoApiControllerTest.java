package com.cassandra.api;

import com.cassandra.model.Ingredient;
import com.cassandra.model.Taco;
import com.cassandra.repository.TacoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TacoApiControllerTest {

//    @Mock
//    private TacoRepository tacoRepo;

    Taco[] tacos = {
            testTaco(UUID.fromString("1")), testTaco(UUID.fromString("2")),
            testTaco(UUID.fromString("3")), testTaco(UUID.fromString("4")),
            testTaco(UUID.fromString("5")), testTaco(UUID.fromString("6")),
            testTaco(UUID.fromString("7")), testTaco(UUID.fromString("8")),
            testTaco(UUID.fromString("9")), testTaco(UUID.fromString("10")),
            testTaco(UUID.fromString("11")), testTaco(UUID.fromString("12")),
            testTaco(UUID.fromString("13")), testTaco(UUID.fromString("14")),
            testTaco(UUID.fromString("15")), testTaco(UUID.fromString("16")),
    };

    @Test
    void recentTacosTest() {
        Flux<Taco> tacoFlux = Flux.just(tacos);
        TacoRepository tacoRepo = mock(TacoRepository.class);
        when(tacoRepo.findAll()).thenReturn(tacoFlux);

        WebTestClient testClient = WebTestClient.bindToController(
                new TacoApiController(tacoRepo)
        ).build();

        testClient.get().uri("/api/tacos?recent")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$").isNotEmpty()
                .jsonPath("$[0].id").isEqualTo(tacos[0].getId().toString())
                .jsonPath("$[0].name").isEqualTo("Taco 1")
                .jsonPath("$[1].id").isEqualTo(tacos[1].getId().toString())
                .jsonPath("$[1].name").isEqualTo("Taco 2")
                .jsonPath("$[11].id").isEqualTo(tacos[11].getId().toString())
                .jsonPath("$[11].name").isEqualTo("Taco 12")
                .jsonPath("$[12]").doesNotExist();

//        OR

        testClient.get().uri("/api/tacos?recent")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Taco.class)
                .contains(Arrays.copyOf(tacos, 12));
    }

    @Test
    void tacoByIdTest() {
        TacoRepository tacoRepo = mock(TacoRepository.class);

        WebTestClient testClient = WebTestClient.bindToController(
                new TacoApiController(tacoRepo)
        ).build();

        Mono<Taco> unsavedTacoMono = Mono.just(testTaco(UUID.fromString("1")));
        Taco savedTaco = testTaco(UUID.fromString("1"));
        Mono<Taco> savedTacoMono = Mono.just(savedTaco);

        when(tacoRepo.save(any())).thenReturn(savedTacoMono);

        testClient.post()
                .uri("/api/tacos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(unsavedTacoMono, Taco.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Taco.class)
                .isEqualTo(savedTaco);
    }

    @Test
    void postTaco() {

    }

    private Taco testTaco(UUID number) {
        Taco taco = new Taco();
        taco.setId(number);
        taco.setName("Taco " + number);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP));
        ingredients.add(new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN));

        taco.addIngredient(new Ingredient("INGA", "Ingredient A", Ingredient.Type.WRAP));
        taco.addIngredient(new Ingredient("INGB", "Ingredient B", Ingredient.Type.PROTEIN));
        return taco;
    }
}