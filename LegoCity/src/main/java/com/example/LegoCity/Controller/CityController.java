package com.example.LegoCity.Controller;

import com.example.LegoCity.Models.Building;
import com.example.LegoCity.Models.CityState;
import com.example.LegoCity.Service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /**
     * Построить здание по типу
     */
    @PostMapping("/build")
    public void build(@RequestParam Long typeId) {
        cityService.build(typeId);
    }
    @PostMapping("/destroy")
    public String destroy(@RequestParam Long typeId){
        cityService.destroy(typeId);
        return "redirect:/city";
    }

    /**
     * Сброс города
     */
    @PostMapping("/reset")
    public void reset() {
        cityService.resetCity();
    }

    /**
     * Добавить кубики (награда / событие)
     */
    @PostMapping("/cubes/add")
    public void addCubes(@RequestParam int amount) {
        cityService.addCubes(amount);
    }

    /**
     * Получить состояние города
     */
    @GetMapping("/state")
    public CityState getState() {
        return cityService.getCityState();
    }

    /**
     * Получить список всех построенных зданий
     */
    @GetMapping("/buildings")
    public List<Building> getBuildings() {
        return cityService.getAllBuildings();
    }
}
