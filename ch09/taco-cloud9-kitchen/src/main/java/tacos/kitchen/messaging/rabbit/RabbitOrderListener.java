package tacos.kitchen.messaging.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tacos.kitchen.controller.KitchenUI;
import tacos.kitchen.model.TacoOrder;

@Component
@RequiredArgsConstructor
@Profile("rabbit")
public class RabbitOrderListener {

    private final KitchenUI ui;

    @RabbitListener(queues = "queue test")
    public void receiveOrder(TacoOrder order) {
        ui.displayOrder(order);
    }
}
