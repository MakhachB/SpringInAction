package com.messaging.messaging;

import com.messaging.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
@RequiredArgsConstructor
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final Destination destination;

    @Override
    public void sendOrder(TacoOrder tacoOrder) {
//        jms.send(session -> session.createObjectMessage(tacoOrder));  // using default-destination
//        jms.send(destination, session -> session.createObjectMessage(tacoOrder));  // using custom destination
//        jms.send("taco.cloud.queue-string-dest", session -> session.createObjectMessage(tacoOrder)); // using string dest

//        jms.convertAndSend(tacoOrder);

        // use message postProcessors
        jms.convertAndSend("tacocloud.order.queue", tacoOrder, this::addOrderSource);
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }

}
