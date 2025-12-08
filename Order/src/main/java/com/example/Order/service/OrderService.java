package com.example.Order.service;

import com.example.Order.repo.OrderRepository;

public class OrderService {

    private OrderRepository repository;


    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public boolean placeOrder(String product, int qty ){
        if(qty <= 0) return false;
        repository.save(product, qty);
        return true;
    }
}
