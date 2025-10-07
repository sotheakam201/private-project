package com.techie.springbootrediscache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloWorld {

    @GetMapping("/home")
    public String home() {
        return "🚀 Hello Render! CI/CD Works! ✅";
    }
}
