package tacos.kitchen.messaging.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.kitchen.messaging.OrderReceiver;
import tacos.kitchen.model.TacoOrder;

@Component
@RequiredArgsConstructor
@Profile("rabbit")
public class RabbitOrderReceiver implements OrderReceiver {

    private final RabbitTemplate rabbit;
//    private final MessageConverter converter;

    @Override
    public TacoOrder receiveOrder() {
        return rabbit.receiveAndConvert("queue test", new ParameterizedTypeReference<>() {});
    }
}
