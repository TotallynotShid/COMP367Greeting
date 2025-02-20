package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalTime;

@RestController
public class controller {
    @GetMapping("/hello")
    public String hello() {
        String name = "Rashid Leroy Ang"; // Replace with your name
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.NOON)) {
            return "Good morning, " + name + ", Welcome to COMP367";
        } else {
            return "Good afternoon, " + name + ", Welcome to COMP367";
        }
    }
}
