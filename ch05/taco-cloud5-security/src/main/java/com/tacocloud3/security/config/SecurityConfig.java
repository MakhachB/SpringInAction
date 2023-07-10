package com.tacocloud3.security.config;

import com.tacocloud3.security.RepositoryUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final RepositoryUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/design", "/orders").hasRole("USER")
//                    .antMatchers("/", "/**").access("permitAll()") // Язык SpEL
//                    .antMatchers("/", "/**").access("principal.accountNonExpired") // Язык SpEL
                .antMatchers("/", "/**").permitAll()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design") // Можно передать true вторым типом, чтобы юзер всегда перенаправлялся сюда
//                ;

                // Allow pages to be loaded in frames from the same origin; needed for H2-Console
                // TODO Изучить
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

//        При включении csrf все запросы кроме get будут блокироваться
                .and()
                .csrf()
                .ignoringAntMatchers("/**")
        ;
    }

//    Actual security method
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return null;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService TestUserDetailsService(PasswordEncoder encoder) {
//        ArrayList<UserDetails> usersList = new ArrayList<>();
//        usersList.add(new User(
//                "buzz", encoder.encode("12345"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        usersList.add(new User(
//                "woody", encoder.encode("12345"),
//                List.of(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(usersList);

//    }
}
