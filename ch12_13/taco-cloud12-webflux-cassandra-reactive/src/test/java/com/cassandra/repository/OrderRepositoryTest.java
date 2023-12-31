package com.cassandra.repository;

import com.cassandra.model.Ingredient;
import com.cassandra.model.Taco;
import com.cassandra.model.TacoOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.test.StepVerifier;

@DataCassandraTest
public class OrderRepositoryTest {

  @Autowired
  OrderRepository orderRepo;

  @BeforeEach
  public void setup() {
    orderRepo.deleteAll().subscribe();
  }

  @Test
  public void shouldSaveAndFetchOrders() {
    TacoOrder order = createOrder();

    StepVerifier
      .create(orderRepo.save(order))
      .expectNext(order)
      .verifyComplete();

    StepVerifier
      .create(orderRepo.findById(order.getId()))
      .expectNext(order)
      .verifyComplete();

    StepVerifier
      .create(orderRepo.findAll())
      .expectNext(order)
      .verifyComplete();
  }

  private TacoOrder createOrder() {
    TacoOrder order = new TacoOrder();
    order.setDeliveryName("Test Customer");
    order.setDeliveryStreet("1234 North Street");
    order.setDeliveryCity("Notrees");
    order.setDeliveryState("TX");
    order.setDeliveryZip("79759");
    order.setCcNumber("4111111111111111");
    order.setCcExpiration("12/23");
    order.setCcCVV("123");
    Taco taco1 = new Taco();
    taco1.setName("Test Taco One");

    taco1.addIngredient(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
    taco1.addIngredient(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
    taco1.addIngredient(new Ingredient("CHED", "Cheddar Cheese", Ingredient.Type.CHEESE));
    order.addTaco(taco1);

    Taco taco2 = new Taco();
    taco2.addIngredient(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
    taco2.addIngredient(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
    taco2.addIngredient(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
    taco2.setName("Test Taco Two");
    order.addTaco(taco2);
    return order;
  }

}
