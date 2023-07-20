package com.messaging.messaging;

import com.messaging.model.TacoOrder;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import javax.jms.Destination;
import java.util.Map;

@Configuration
public class MessageConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
//        Map<String, Class<?>> typeIdMappings = new HashMap<>();
//        typeIdMappings.put("order", TacoOrder.class);
        messageConverter.setTypeIdMappings(Map.of("order", TacoOrder.class));
        return messageConverter;
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue-dest");
    }
}
