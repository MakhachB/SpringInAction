package com.tacocloud3.model.udt.utils;

import com.tacocloud3.model.Ingredient;
import com.tacocloud3.model.udt.IngredientUDT;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class IngredientUDTUtils {
    public IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }

    public Ingredient toIngredient(IngredientUDT ingredientUDT) {
        return new Ingredient(null, ingredientUDT.getName(), ingredientUDT.getType());
    }

    public List<IngredientUDT> toIngredientUDTs(List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(IngredientUDTUtils::toIngredientUDT)
                .collect(Collectors.toList());
    }

    public List<Ingredient> toIngredients(List<IngredientUDT> ingredientUDTS) {
        return ingredientUDTS.stream()
                .map(IngredientUDTUtils::toIngredient)
                .collect(Collectors.toList());
    }
}