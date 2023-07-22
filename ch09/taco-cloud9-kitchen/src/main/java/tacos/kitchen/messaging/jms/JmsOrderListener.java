package tacos.kitchen.messaging.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import tacos.kitchen.controller.KitchenUI;
import tacos.kitchen.model.TacoOrder;

@RequiredArgsConstructor
@Component
@Profile("jms-listener")
public class JmsOrderListener {

    private final KitchenUI ui;

//    @JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(TacoOrder tacoOrder) {
        ui.displayOrder(tacoOrder);
    }
}
