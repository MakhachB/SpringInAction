package com.tacocloud8rest.model.dto;

import com.tacocloud8rest.model.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDTO {
    private String id;
    private String name;
    private String type;

    public IngredientDTO(Ingredient ingredient) {
        this.id = ingredient.getId();
        this.name = ingredient.getName();
        this.type = ingredient.getType().name();
    }

    public Ingredient toEntity() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(this.id);
        ingredient.setName(this.name);
        ingredient.setType(Ingredient.Type.valueOf(this.type));
        return ingredient;
    }
}
