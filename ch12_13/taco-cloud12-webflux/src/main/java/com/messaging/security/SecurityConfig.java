package com.messaging.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity // allow security annotations
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
                .authorizeExchange()
//                .antMatchers("/design/**", "/orders/**").hasRole("USER")
                    .pathMatchers("/", "/**").permitAll()

                .and()
                    .formLogin()
                        .loginPage("/login")
//                        .defaultSuccessUrl("/design") // Можно передать true вторым типом, чтобы юзер всегда перенаправлялся сюда

                .and()
                .logout() // By default, works on '/logout'
//                        .logoutSuccessUrl("/") // without this user will be redirected on '/login'

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                // TODO Изучить
//                .and()
//                    .headers()
//                        .frameOptions()
//                            .sameOrigin()

                // При включении csrf все запросы из форм кроме get будут блокироваться
                .and()
                .csrf()
                .disable()
//                        .ignoringAntMatchers("/api/**")

//                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
