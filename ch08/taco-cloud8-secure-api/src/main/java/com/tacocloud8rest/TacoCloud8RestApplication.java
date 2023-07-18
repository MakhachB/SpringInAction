package com.tacocloud8rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TacoCloud8RestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloud8RestApplication.class, args);
    }

}
//curl --location "http://localhost:8080/api/ingredients" --header "Content-Type: application/json" -u test2:12345 --data "{\"id\": \"КУРЦ\",\"name\": \"Курица\",\"type\": \"PROTEIN\"}"