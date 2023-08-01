package rsocket.request_response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@Slf4j
public class RsocketClientConfiguration {

//    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requestBuilder) {
        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);

            tcp
                    .route("greeting")
                    .data("Ассалам-у Алейкум!")
                    .retrieveMono(String.class)
                    .subscribe(responce -> log.info("Got a response: {}", responce));

            String who = "Craig";
            tcp
                    .route("greeting/{name}", who)
                    .data("Ассалам-у Алейкум!")
                    .retrieveMono(String.class)
                    .subscribe(responce -> log.info("Got a response: {}", responce));


        };
    }
}
