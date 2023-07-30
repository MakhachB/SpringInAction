package com.messaging.messaging.jms;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("jms")
public class MessageConfig {

//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
////        Map<String, Class<?>> typeIdMappings = new HashMap<>();
////        typeIdMappings.put("order", TacoOrder.class);
//        messageConverter.setTypeIdMappings(Map.of("order", TacoOrder.class));
//        return messageConverter;
//    }

//    @Bean
//    public Destination orderQueue() {
//        return new ActiveMQQueue("tacocloud.order.queue-dest");
//    }
}
