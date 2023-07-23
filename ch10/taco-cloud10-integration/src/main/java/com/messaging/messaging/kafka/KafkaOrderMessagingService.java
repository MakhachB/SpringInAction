package com.messaging.messaging.kafka;

import com.messaging.messaging.OrderMessagingService;
import com.messaging.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("kafka")
public class KafkaOrderMessagingService implements OrderMessagingService {

//    private final KafkaTemplate<String, TacoOrder> kafka;

    @Override
    public void sendOrder(TacoOrder order) {
//        kafka.send("tacocloud.orders.topic", order);
//        kafka.sendDefault(order);
    }
}
