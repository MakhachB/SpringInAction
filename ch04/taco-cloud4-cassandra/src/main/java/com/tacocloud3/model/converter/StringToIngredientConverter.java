package com.tacocloud3.model.converter;

import com.tacocloud3.model.udt.IngredientUDT;
import com.tacocloud3.model.udt.utils.IngredientUDTUtils;
import com.tacocloud3.model.udt.utils.TacoUDTUtils;
import com.tacocloud3.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StringToIngredientConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepository;

    @Override
    public IngredientUDT convert(String id) {
        return ingredientRepository
                .findById(id)
                .map(IngredientUDTUtils::toIngredientUDT)
                .orElse(null);
    }
}
