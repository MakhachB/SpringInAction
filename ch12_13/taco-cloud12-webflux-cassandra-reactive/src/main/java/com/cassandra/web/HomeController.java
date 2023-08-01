package com.cassandra.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@AuthenticationPrincipal User user) {
        System.out.println(user);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
