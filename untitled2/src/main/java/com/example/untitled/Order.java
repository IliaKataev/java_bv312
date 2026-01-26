package com.example.untitled;

public class Order {
    private Long id;
    private String product;


    public Order(Long id,String product) {
        this.product = product;
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
