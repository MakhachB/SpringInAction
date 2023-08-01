package com.cassandra.messaging.jms;

import com.cassandra.messaging.OrderMessagingService;
import com.cassandra.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Profile("jms")
public class JmsOrderMessagingService implements OrderMessagingService {

//    private final JmsTemplate jms;
//    private final Destination destination;

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
////        jms.send(session -> session.createObjectMessage(tacoOrder));  // using default-destination
////        jms.send(destination, session -> session.createObjectMessage(tacoOrder));  // using custom destination
////        jms.send("taco.cloud.queue-string-dest",
////                session -> session.createObjectMessage(tacoOrder)); // using string dest
//
////        jms.convertAndSend(tacoOrder);
//
//        // use message postProcessors
//        jms.convertAndSend("tacocloud.order.queue", tacoOrder, this::addOrderSource);
    }

//    private Message addOrderSource(Message message) throws JMSException {
//        message.setStringProperty("X_ORDER_SOURCE", "WEB");
//        return message;
//    }
}
