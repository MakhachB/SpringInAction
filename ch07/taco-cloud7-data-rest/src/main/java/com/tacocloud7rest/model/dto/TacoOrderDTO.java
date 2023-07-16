package com.tacocloud7rest.model.dto;

import com.tacocloud7rest.model.Taco;
import com.tacocloud7rest.model.TacoOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacoOrderDTO implements Serializable {
    private static final long serialVersionID = 1L;

    private Long id;

    private Date placedAt;

    private UserDTO user;

    private String deliveryName;

    private String deliveryStreet;

    private String deliveryCity;

    private String deliveryState;

    private String deliveryZip;

    private String ccNumber;

    private String ccExpiration;

    private String ccCVV;

    private List<TacoDTO> tacos = new ArrayList<>();

    public TacoOrderDTO(TacoOrder tacoOrder) {
        this.id = tacoOrder.getId();
        this.placedAt = tacoOrder.getPlacedAt();
        this.user = new UserDTO(tacoOrder.getUser());
        this.deliveryName = tacoOrder.getDeliveryName();
        this.deliveryStreet = tacoOrder.getDeliveryStreet();
        this.deliveryCity = tacoOrder.getDeliveryCity();
        this.deliveryState = tacoOrder.getDeliveryState();
        this.deliveryZip = tacoOrder.getDeliveryZip();
        this.ccNumber = tacoOrder.getCcNumber();
        this.ccExpiration = tacoOrder.getCcExpiration();
        this.ccCVV = tacoOrder.getCcCVV();
        for (Taco taco : tacoOrder.getTacos()) {
            this.tacos.add(new TacoDTO(taco));
        }
    }

    public TacoOrder toEntity() {
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setId(this.id);
        tacoOrder.setPlacedAt(this.placedAt);
        tacoOrder.setUser(this.user.toEntity());
        tacoOrder.setDeliveryName(this.deliveryName);
        tacoOrder.setDeliveryStreet(this.deliveryStreet);
        tacoOrder.setDeliveryCity(this.deliveryCity);
        tacoOrder.setDeliveryState(this.deliveryState);
        tacoOrder.setDeliveryZip(this.deliveryZip);
        tacoOrder.setCcNumber(this.ccNumber);
        tacoOrder.setCcExpiration(this.ccExpiration);
        tacoOrder.setCcCVV(this.ccCVV);
        for (TacoDTO tacoDTO : this.tacos) {
            tacoOrder.addTaco(tacoDTO.toEntity());
        }
        return tacoOrder;
    }
}
