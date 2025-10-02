package com.upc.greeting.interfaces;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greetings")
public class Greeting {

    @GetMapping
    public String greeting() {
        return "Greetings from Spring Boot!";
    }
}
