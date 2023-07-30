package com.messaging.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.LinkedHashSet;
import java.util.Set;

@Data
public class TacoOrder {

    @Id
    private Long id;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;

    private Set<Long> tacoIds = new LinkedHashSet<>();

    public void addTaco(Taco taco) {
        tacoIds.add(taco.getId());
    }
}