package com.tacocloud8rest.model.dto;

import com.tacocloud8rest.model.Taco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacoDTO {
    private Long id;
    private String name;
    private List<IngredientDTO> ingredients = new ArrayList<>();

    public TacoDTO(Taco taco) {
        this.id = taco.getId();
        this.name = taco.getName();
        taco.getIngredients().forEach(ingredient -> this.ingredients.add(new IngredientDTO(ingredient)));
    }

    public Taco toEntity() {
        Taco taco = new Taco();
        taco.setId(this.id);
        taco.setName(this.name);
        this.ingredients.forEach(ingredientDTO ->
                taco.addIngredient(ingredientDTO.toEntity()));
        return taco;
    }
}
