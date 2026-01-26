package com.example.untitled;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final AtomicLong counter = new AtomicLong();
    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public Order createOrder(@RequestParam String product){
        Long id = counter.incrementAndGet();
        Order order = new Order(id, product);

        System.out.println("Order создан: " + order.getId());

        restTemplate.postForObject(
                "http://localhost:8083/notify?orderId="+id,
                null,
                String.class
        );

        restTemplate.postForObject(
                "http://localhost:8085/log?orderId="+id,
                null,
                String.class
        );

        return order;
    }
}
