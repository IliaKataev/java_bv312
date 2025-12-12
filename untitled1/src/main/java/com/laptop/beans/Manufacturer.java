package com.laptop.beans;

import java.io.Serializable;

public class Manufacturer implements Serializable {
    private static final long serialVersionUID = 1L;


    private int id;
    private String name;
    private String country;
    private String logoUrl;
    private int employees_count;
    private String description;

    public Manufacturer(){}

    public Manufacturer(int id, String name, String country, String logoUrl, int employees_count, String description) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.logoUrl = logoUrl;
        this.employees_count = employees_count;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public int getEmployees_count() {
        return employees_count;
    }

    public void setEmployees_count(int employees_count) {
        this.employees_count = employees_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", employees_count=" + employees_count +
                ", description='" + description + '\'' +
                '}';
    }
}
