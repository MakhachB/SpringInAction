package com.tacocloud3.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class TacoOrder implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    private String id;

    private Date placedAt = new Date();

//    @Column(name = "delivery_name")
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street name is required")
    private String deliveryStreet;

    @NotBlank(message = "City name is required")
    private String deliveryCity;

    @NotBlank(message = "State is required")
    private String deliveryState;

    @NotBlank(message = "Zip code is required")
    private String deliveryZip;

    //    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;

//    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9][0-9])$",
//            message = "Must be formatted MM/YY")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;

    public List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}