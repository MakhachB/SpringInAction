package com.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.messaging", "tacos"})
public class TacoCloud9MessagingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloud9MessagingApplication.class, args);
    }

}
