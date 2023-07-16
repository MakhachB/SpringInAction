package com.tacocloud7rest.repository;

import com.tacocloud7rest.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}

