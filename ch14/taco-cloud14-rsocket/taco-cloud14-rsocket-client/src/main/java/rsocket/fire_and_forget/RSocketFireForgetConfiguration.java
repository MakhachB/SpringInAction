package rsocket.fire_and_forget;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import rsocket.req_stream.StockQuote;

import java.time.Instant;

@Configuration
@Slf4j
public class RSocketFireForgetConfiguration {

//    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
        return args -> {

            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);

                tcp
                        .route("alert")
                        .data(new Alert(Alert.Level.RED, "Craig", Instant.now()))
                        .send()
                        .subscribe();
                log.info("Alert sent");
        };
    }
}
