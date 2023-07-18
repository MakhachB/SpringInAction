package com.tacocloud8rest;

import com.tacocloud8rest.user.Role;
import com.tacocloud8rest.user.User;
import com.tacocloud8rest.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
    @Bean
    public ApplicationRunner dataLoader(
            UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            repo.save(
                    new User("habuma", encoder.encode("password"), Role.ROLE_ADMIN));
            repo.save(
                    new User("tacochef", encoder.encode("password"), Role.ROLE_ADMIN));
        };
    }
}