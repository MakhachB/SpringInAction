package com.tacocloud3.repository;

import com.tacocloud3.model.IngredientRef;
import com.tacocloud3.model.Taco;
import com.tacocloud3.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@RequiredArgsConstructor
@Primary
public class JdbcTemplateOrderRepository implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        int id = jdbcTemplate.update("INSERT into TACO_ORDER" +
                        "(DELIVERY_NAME, DELIVERY_STREET, DELIVERY_CITY, DELIVERY_STATE," +
                        "DELIVERY_ZIP, CC_NUMBER, CC_EXPIRATION, CC_CVV, PLACED_AT) VALUES " +
                        "(?, ?, ? ,? ,? ,?, ?, ?, ?)",
                order.getDeliveryName(), order.getDeliveryStreet(),
                order.getDeliveryCity(), order.getDeliveryState(),
                order.getDeliveryZip(), order.getCcNumber(),
                order.getCcExpiration(), order.getCcCVV(),
                new Date());


        saveTacos(order.getTacos(), id);
        return order;
    }

    private void saveTacos(List<Taco> tacos, long id) {
        AtomicInteger counter = new AtomicInteger();
        tacos.forEach(taco -> {
            int tacoId = jdbcTemplate.update(
                    "INSERT INTO TACO " +
                            "(NAME, TACO_ORDER, TACO_ORDER_KEY, CREATED_AT) VALUES " +
                            "(?, ?, ?, ?)",
                    taco.getName(),
                    id,
                    counter.incrementAndGet(),
                    new Date()
            );
            saveIngredientRefs(taco.getIngredients(), tacoId);
        });

    }

    private void saveIngredientRefs(List<IngredientRef> ingredients, int tacoId) {
        AtomicInteger counter = new AtomicInteger();
        ingredients.forEach(ingRef -> jdbcTemplate.update(
                "insert into Ingredient_Ref (ingredient, taco, taco_key) "
                        + "values (?, ?, ?)",
                ingRef.getIngredient(), tacoId, counter.incrementAndGet()));
    }
}
