package com.messaging.api;

import com.messaging.model.Ingredient;
import com.messaging.model.Ingredient.Type;
import com.messaging.model.Taco;
import com.messaging.repository.TacoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TacoApiControllerTest {

//    @Mock
//    private TacoRepository tacoRepo;

    Taco[] tacos = {
            testTaco(1L), testTaco(2L),
            testTaco(3L), testTaco(4L),
            testTaco(5L), testTaco(6L),
            testTaco(7L), testTaco(8L),
            testTaco(9L), testTaco(10L),
            testTaco(11L), testTaco(12L),
            testTaco(13L), testTaco(14L),
            testTaco(15L), testTaco(16L)
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

        Mono<Taco> unsavedTacoMono = Mono.just(testTaco(1L));
        Taco savedTaco = testTaco(1L);
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

    private Taco testTaco(Long number) {
        Taco taco = new Taco();
        taco.setId(number);
        taco.setName("Taco " + number);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(new Ingredient("INGA", "Ingredient A", Type.WRAP));
        ingredients.add(new Ingredient("INGB", "Ingredient B", Type.PROTEIN));

        taco.addIngredient(new Ingredient("INGA", "Ingredient A", Type.WRAP));
        taco.addIngredient(new Ingredient("INGB", "Ingredient B", Type.PROTEIN));
        return taco;
    }
}