package com.messaging.security;

import com.messaging.repository.UserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
//@EnableMethodSecurity // allow security annotations
//@EnableWebFluxSecurity
public class SecurityConfig {
    private final UserRepository userRepo;

//    @Bean
//    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
//        return http
//                .authorizeExchange()
////                .pathMatchers("/design/**", "/orders/**")
////                    .hasRole("USER")
//                .anyExchange().permitAll()
//
//                .and()
//                    .formLogin()
//                        .loginPage("/login")
////                        .defaultSuccessUrl("/design")
//                .and()
//                    .logout() // By default, works on '/logout'
//                // При включении csrf все запросы кроме get будут блокироваться
//                .and()
//                    .csrf()
//                        .disable()
////                        .ignoringAntMatchers("/api/**")
//                .build();
//    }

//    @Bean
//    public ReactiveUserDetailsService userDetailsService() {
////        return username -> userRepo.findByUsername(username).cast(UserDetails.class);
//        return username -> userRepo
//                .findByUsername(username)
//                .map(user -> (UserDetails) user)
////                .handle((user, sink) -> {
////                    if (user == null) {
////                        sink.error(new UsernameNotFoundException("User '" + username + "' not found"));
////                    }
////                })
//                ;
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
