package com.theinkwell.server.domains.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    
    @GetMapping("greetings")
    public String greeting() {
        return "Hello admin";
    }
}
