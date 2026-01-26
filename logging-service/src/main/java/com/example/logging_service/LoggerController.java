package com.example.logging_service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {
    @PostMapping("/log")
    public String log(@RequestParam Long orderId){
        System.out.println("Order " + orderId + " залогирован");
        return "Logged";
    }
}
