package com.messaging.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Taco {

//    private static final long serialVersionID = 1L;

    @Id
    private Long id;

    @NotNull
    @Size(min = 5, max = 30, message = "Name must be at least 5 characters long")
    private @NonNull String name;

    @Size(min = 1, message = "You must choose at least 1 ingredient")
    private Set<Long> ingredientIds = new HashSet<>();

    public void addIngredient(Ingredient ingredient) {
        ingredientIds.add(ingredient.getId());
    }
}
