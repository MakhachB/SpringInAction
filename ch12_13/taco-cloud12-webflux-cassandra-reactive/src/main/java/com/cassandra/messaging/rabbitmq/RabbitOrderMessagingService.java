package com.cassandra.messaging.rabbitmq;

import com.cassandra.messaging.OrderMessagingService;
import com.cassandra.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("rabbit")
public class RabbitOrderMessagingService implements OrderMessagingService {

//    private final RabbitTemplate rabbit;

    @Override
    public void sendOrder(TacoOrder order) {
//        MessageConverter converter = rabbit.getMessageConverter();
//        MessageProperties props = new MessageProperties();
//        props.setHeader("X_ORDER_SOURCE", "WEB");
//        Message message = converter.toMessage(order, props);
//        rabbit.send("tacocloud.order.queue", message);

//        OR

//        rabbit.convertAndSend("taco.order.queue", order, message -> {
//            MessageProperties props = message.getMessageProperties();
//            props.setHeader("X_ORDER_SOURCE", "WEB");
//            return message;
//        });
    }
}
