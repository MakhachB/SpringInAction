package com.messaging.model.converter;

import com.messaging.model.Ingredient;
import com.messaging.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, Mono<Ingredient>> {

    private final IngredientRepository ingredientRepository;

    @Override
    public Mono<Ingredient> convert(String id) {
        return ingredientRepository.findById(id);
    }
}
