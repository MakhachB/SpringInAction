package com.tacocloud8rest.repository;

import com.tacocloud8rest.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}

