package rsocket.req_stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@Slf4j
public class RSocketReqStreamConfiguration {

//    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            String stockSymbol = "XYZ";

            RSocketRequester tcp = requesterBuilder.tcp("localhost", 7000);

            for (int i = 0; i < 10; i++) {
                tcp
                        .route("stock/{symbol}", stockSymbol)
                        .retrieveFlux(StockQuote.class)
                        .doOnNext(stockQuote ->
                                log.info("Price of {} : {} (at {})",
                                        stockQuote.getSymbol(),
                                        stockQuote.getPrice(),
                                        stockQuote.getTimestamp()))
                        .subscribe();
            }

        };
    }
}
