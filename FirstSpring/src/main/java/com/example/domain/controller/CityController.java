package com.example.domain.controller;

import com.example.domain.City;
import com.example.domain.service.CityService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/city")
public class CityController {

    private final CityService service;

    public CityController(CityService service){
        this.service=service;
    }

    @GetMapping
    public String cityPage(Model model){
        City city = service.getCityState();
        model.addAttribute("city", city);
        model.addAttribute("buildings", city.getBuildings());
        return "city";
    }

    @GetMapping("/build")
    public String buildHouse(@RequestParam String type, Model model){
        try{
            service.build(type);
        } catch (IllegalStateException | IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
        }
        City city = service.getCityState();
        model.addAttribute("city", city);
        model.addAttribute("buildings", city.getBuildings());
        return "city";
    }

    @GetMapping("/addBricks")
    public String addBricks(@RequestParam int amount, Model model){
        if(amount <= 0) {
            model.addAttribute("error", "Введите положительное число кубиков");
        } else {
            service.addBricks(amount);
        }
        City city = service.getCityState();
        model.addAttribute("city", city);
        model.addAttribute("buildings", city.getBuildings());
        return "city";
    }

}
