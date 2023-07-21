package tacos.kitchen.messaging.jms;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import tacos.kitchen.messaging.OrderReceiver;
import tacos.kitchen.model.TacoOrder;

@Component
@RequiredArgsConstructor
@Profile({"jms-template", "jms-listener"})
public class JmsOrderReceiver implements OrderReceiver {

    private final JmsTemplate jms;
//    private final MessageConverter converter;

    @Override
    public TacoOrder receiveOrder() {
//        Message message = jms.receive("tacocloud.order.queue");
//        return (TacoOrder) converter.fromMessage(message);
        TacoOrder tacoOrder = (TacoOrder) jms.receiveAndConvert("tacocloud.order.queue");
        return tacoOrder;
    }
}
