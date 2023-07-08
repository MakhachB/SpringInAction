package com.tacocloud3.model.udt;

import com.tacocloud3.model.Ingredient;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("taco")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class TacoUDT {

    private String name;

    private List<IngredientUDT> ingredients;
}
