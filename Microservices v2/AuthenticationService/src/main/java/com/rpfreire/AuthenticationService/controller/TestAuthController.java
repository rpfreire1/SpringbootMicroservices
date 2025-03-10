package com.rpfreire.AuthenticationService.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class TestAuthController {
    @GetMapping("/get")
    public String helloGet() {
        return "Hello World!";
    }
    @PostMapping("/post")
    public String helloPost() {
        return "Hello World!";
    }
    @PutMapping("/put")
    public String helloPut() {
        return "Hello World!";
    }
    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello World!";
    }
    @PatchMapping("/patch")
    public String helloPatch() {
        return "Hello World!";
    }
}
