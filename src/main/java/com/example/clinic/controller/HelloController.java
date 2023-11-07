package com.example.clinic.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class HelloController {
    @GetMapping(value="/")
    public String getMethodName() {
        return "hello meu mundo";
    }
    
}
