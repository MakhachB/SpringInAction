package tacos.kitchen.messaging.jms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import tacos.kitchen.model.TacoOrder;

import java.util.Map;

@Profile({"jms-template", "jms-listener"})
@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");
        messageConverter.setTypeIdMappings(Map.of("order", TacoOrder.class));
        return messageConverter;
    }

}
