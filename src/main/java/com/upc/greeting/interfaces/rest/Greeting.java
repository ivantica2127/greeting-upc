package com.upc.greeting.interfaces.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/greetings")
public class Greeting {

    @GetMapping
    public String greeting(@RequestParam(defaultValue = "world") String nombre) {
        return "Greetings from Spring Boot!"+nombre;
    }
}
