package com.laptop.dao;

import com.laptop.beans.Manufacturer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManufacturerDAO {
    private static ManufacturerDAO instance;
    private final List<Manufacturer> manufacturers;


    private ManufacturerDAO() {
        manufacturers = new ArrayList<>();
        initializeData();
    }

    public static synchronized ManufacturerDAO getInstance(){
        if(instance==null){
            instance = new ManufacturerDAO();
        }
        return instance;
    }

    private void initializeData(){
        manufacturers.add(new Manufacturer(
           1,
           "IliaCORP",
           "Iliastan",
           "images/logo/1.jpg",
           150,
           "Best Corporation in da world"
        ));
    }

    public List<Manufacturer> getAllManufacturers(){
        return new ArrayList<>(manufacturers);
    }

    public Optional<Manufacturer> getManufacturerById(int id){
        return manufacturers.stream()
                .filter(m -> m.getId() == id)
                .findFirst();
    }
    //получить по стране, получить количество производителей, поиск производителя по названию

    public int getManufacturerCount(){
        return  manufacturers.size();
    }

    public List<Manufacturer> getManufacturerByCountry(String country) {
        return manufacturers.stream()
                .filter(m -> m.getCountry().equalsIgnoreCase(country))
                .toList();
    }

    public List<Manufacturer> searchManufacturers(String query){
        String lowerQuery = query.toLowerCase();
        return manufacturers.stream()
                .filter(m -> m.getName().toLowerCase().contains(lowerQuery) ||
                        m.getCountry().toLowerCase().contains(lowerQuery) ||
                        m.getDescription().toLowerCase().contains(lowerQuery))
                .toList();
    }
}
