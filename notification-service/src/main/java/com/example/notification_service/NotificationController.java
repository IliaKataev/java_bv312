package com.example.notification_service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @PostMapping("/notify")
    public String notify(@RequestParam Long orderId){
        System.out.println("Notification выслан по заказу: "+orderId);
        return "Notification было отправлено";
    }

}
