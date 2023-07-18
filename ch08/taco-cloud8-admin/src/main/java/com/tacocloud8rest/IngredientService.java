package com.tacocloud8rest;

public interface IngredientService {

  Iterable<Ingredient> findAll();
  
  Ingredient addIngredient(Ingredient ingredient);
    
}
