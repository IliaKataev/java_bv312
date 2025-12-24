package com.example.domain.controller;

import com.example.domain.City;
import com.example.domain.service.CityService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {

    private final CityService service;

    public CityController(CityService service){
        this.service=service;
    }

    @PostMapping("/build/house")
    public String buildHouse(){
        service.buildHouse();
        return "redirect:/city";
    }

    @GetMapping
    public String cityPage(Model model){
        City city = service.getCityState();
        model.addAttribute("city", city);
        model.addAttribute("buildings", city.getBuildings());
        return "city.html";
    }
}
