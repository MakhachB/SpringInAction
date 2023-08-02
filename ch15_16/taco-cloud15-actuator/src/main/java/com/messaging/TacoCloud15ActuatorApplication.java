package com.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TacoCloud15ActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloud15ActuatorApplication.class, args);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080/api");
    }
}
