package com.danest.backend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {
    @GetMapping("/")
    String helloWorld() {
        return "It Works!";
    }

    @PostMapping("/test")
    boolean security() {
        return true;
    }
}
