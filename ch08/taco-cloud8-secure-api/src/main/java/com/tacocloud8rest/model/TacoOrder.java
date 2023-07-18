package com.tacocloud8rest.model;

import com.tacocloud8rest.security.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "taco_order")
public class TacoOrder implements Serializable {

    private static final long serialVersionID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    @ManyToOne
    private User user;

//    @Column(name = "delivery_name")
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;

    @NotBlank(message = "Street name is required")
    @Column(length = 50, nullable = false, unique = true)
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }

    @PrePersist
    void createdAt() {
        placedAt = new Date();
    }

    @Override
    public String toString() {
        return "TacoOrder{" +
                "id=" + id +
                ", placedAt=" + placedAt +
                ", user=" + user +
                ", deliveryName='" + deliveryName + '\'' +
                ", deliveryStreet='" + deliveryStreet + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", deliveryState='" + deliveryState + '\'' +
                ", deliveryZip='" + deliveryZip + '\'' +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccExpiration='" + ccExpiration + '\'' +
                ", ccCVV='" + ccCVV + '\'' +
                '}';
    }
}