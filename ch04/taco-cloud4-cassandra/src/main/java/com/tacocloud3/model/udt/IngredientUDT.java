package com.tacocloud3.model.udt;

import com.tacocloud3.model.Ingredient;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {

//    В UDT необязательно хранить id оригинального объекта
//    private String id;

    private final String name;
    private final Ingredient.Type type;
}
