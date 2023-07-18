package com.tacocloud8rest.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity // allow security annotations
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/ingredients*/**").hasAuthority("SCOPE_writeIngredients")
                .antMatchers(HttpMethod.DELETE, "/api/ingredients/*").hasAuthority("SCOPE_deleteIngredients")
                .antMatchers("/", "/**").permitAll()

                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/design") // Можно передать true вторым типом, чтобы юзер всегда перенаправлялся сюда

                .and()
                    .logout() // By default, works on '/logout'
                        .logoutSuccessUrl("/") // without this user will be redirected on '/login'

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                // TODO Изучить
                .and()
                    .headers()
                        .frameOptions()
                            .sameOrigin()

                // При включении csrf все запросы из форм кроме get будут блокироваться
                .and()
                    .csrf()
                        .ignoringAntMatchers("/api/**", "/data-api/**")


                .and()
                    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
