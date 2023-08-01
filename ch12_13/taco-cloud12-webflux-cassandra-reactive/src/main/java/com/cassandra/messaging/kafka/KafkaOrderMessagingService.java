package com.cassandra.messaging.kafka;

import com.cassandra.messaging.OrderMessagingService;
import com.cassandra.model.TacoOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//@Profile("kafka")
public class KafkaOrderMessagingService implements OrderMessagingService {

    private final KafkaTemplate<String, TacoOrder> kafka;

    @Override
    public void sendOrder(TacoOrder order) {
//        kafka.send("tacocloud.orders.topic", order);
        kafka.sendDefault(order);
    }
}
