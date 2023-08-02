package com.messaging.actuator.metrics;

import com.messaging.model.Ingredient;
import com.messaging.model.Taco;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TacoMetrics
//        extends AbstractRepositoryEventListener<Taco>
{

    private final MeterRegistry meterRegistry;

//    @Override
    protected void onAfterCreate(Taco taco) {
        for (Ingredient ingredient : taco.getIngredients()) {
            meterRegistry.counter("tacocloud", "ingredient",
                    ingredient.getId()).increment();
        }

    }
}
