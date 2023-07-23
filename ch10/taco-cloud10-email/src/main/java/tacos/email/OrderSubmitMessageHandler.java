package tacos.email;

import lombok.RequiredArgsConstructor;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import tacos.email.props.ApiProperties;

@Component
@RequiredArgsConstructor
public class OrderSubmitMessageHandler implements GenericHandler<EmailOrder> {

    private final RestTemplate rest;
    private final ApiProperties props;

    @Override
    public Object handle(EmailOrder payload, MessageHeaders headers) {
        rest.postForObject(props.getUrl(), payload, String.class);
        return null;
    }
}
