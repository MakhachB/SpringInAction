package com.cassandra.web;

import com.cassandra.repository.UserRepository;
import com.cassandra.security.RegistrationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String registerFrom() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(Mono<RegistrationForm> form) {
//        userRepository.saveAll(form.toUser(passwordEncoder));
        form
                .flatMap(rf -> Mono.just(rf.toUser(passwordEncoder)))
                .flatMap(userRepository::save)
                .subscribe();
        return "redirect:/login";
    }

}
