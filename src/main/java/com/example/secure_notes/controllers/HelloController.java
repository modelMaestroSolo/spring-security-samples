package com.example.secure_notes.controllers;

import org.springframework.web.bind.annotation.*;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

}
