package com.tacocloud3.model.udt.utils;

import com.tacocloud3.model.Ingredient;
import com.tacocloud3.model.Taco;
import com.tacocloud3.model.udt.IngredientUDT;
import com.tacocloud3.model.udt.TacoUDT;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TacoUDTUtils {

    public TacoUDT toTacoUDT(Taco taco) {
        return new TacoUDT(taco.getName(), taco.getIngredients());
    }

    public Taco toTaco(TacoUDT tacoUDT) {
        return new Taco(tacoUDT.getName(), tacoUDT.getIngredients());
    }

    public List<TacoUDT> toTacoUDTs(List<Taco> tacos) {
        return tacos.stream()
                .map(TacoUDTUtils::toTacoUDT)
                .collect(Collectors.toList());
    }

    public List<Taco> toTacos(List<TacoUDT> tacoUDTs) {
        return tacoUDTs.stream()
                .map(TacoUDTUtils::toTaco)
                .collect(Collectors.toList());
    }


}
