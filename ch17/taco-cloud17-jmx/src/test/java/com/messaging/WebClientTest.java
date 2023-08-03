package com.messaging;

import com.messaging.model.Ingredient;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebClientTest {

    private final WebClient webClient;

    @Test
    void ClientGetTest() {
        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("/ingredients/{id}", "FLTO")
                .retrieve()
                .bodyToMono(Ingredient.class);
        ingredientMono.subscribe(System.out::println);

        Flux<Ingredient> ingredientFlux = webClient
                .get()
                .uri("/ingredients")
                .retrieve()
                .bodyToFlux(Ingredient.class);
        ingredientFlux
                .timeout(Duration.ofSeconds(1))
                .subscribe(System.out::println, System.out::println);
    }

    @Test
    void ClientPostTest() {
        Ingredient ingredient = new Ingredient("INGC", "Ingredient C", Ingredient.Type.VEGGIES);
        Mono<Ingredient> ingredientMono = Mono.just(ingredient);

        Mono<Ingredient> result = webClient
                .post()
                .uri("/ingredients")
//                .body(ingredientMono, Ingredient.class)  // OR
                .bodyValue(ingredient)
                .retrieve()
                .bodyToMono(Ingredient.class);

        result.subscribe(System.out::println);

        Flux<Ingredient> ingredientFlux = webClient
                .get()
                .uri("/ingredients")
                .retrieve()
                .bodyToFlux(Ingredient.class);

        ingredientFlux
                .timeout(Duration.ofSeconds(1))
                .subscribe(System.out::println, System.out::println);

    }

    @Test
    void ClientPutTest() {
        Ingredient ingredient = new Ingredient("INGC", "Ingredient C", Ingredient.Type.VEGGIES);

        Mono<Void> result = webClient
                .put()
                .uri("/ingredients/{id}", ingredient.getId())
//                .body(ingredientMono, Ingredient.class)  // OR
                .bodyValue(ingredient)
                .retrieve()
                .bodyToMono(Void.class);

        result.subscribe();
    }

    @Test
    void ClientDeleteTest() {

        Mono<Void> result = webClient
                .delete()
                .uri("/ingredients/{id}", "FLTO")
                .retrieve()
                .bodyToMono(Void.class);

        result.subscribe();
    }

    @Test
    void ClientGetWithSubscriberHandlingTest() {
        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("/ingredients/{id}", "FLTO")
                .retrieve()
                .bodyToMono(Ingredient.class);

        ingredientMono.subscribe(
                System.out::println,
                Throwable::printStackTrace
        );
    }

    @Test
    void ClientGetWithClientHandlingTest() {
        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("/ingredients/{id}", "FLTO")
                .retrieve()
                .onStatus(
                        status -> status.equals(HttpStatus.NOT_FOUND),
                        response -> Mono.just(new UnknownIngredientException()))
                .onStatus(HttpStatus::is5xxServerError,
                        response -> Mono.just(new RuntimeException("Server exception")))
                .bodyToMono(Ingredient.class);

        ingredientMono.subscribe(
                System.out::println,
                Throwable::printStackTrace
        );
    }
    private static class UnknownIngredientException extends RuntimeException {
    }

    @Test
    void ClientExchangeTest() {
        Mono<Ingredient> ingredientMono = webClient
                .get()
                .uri("/ingredients/{id}", "FLTO")
                .exchangeToMono(cr -> cr.bodyToMono(Ingredient.class)); // Эквивалент retrieve()

        ingredientMono.subscribe();

        Mono<Ingredient> ingredientMono2 = webClient
                .get()
                .uri("/ingredients/{id}", "FLTO")
                .exchangeToMono(cr -> {
                    if (cr.headers().header("X_UNAVAILABLE").contains("true")) {
                        return Mono.empty();
                    }
                    return Mono.just(cr);
                })
                .flatMap(cr -> cr.bodyToMono(Ingredient.class));

        ingredientMono2.subscribe();
    }
}

