package com.example.LegoCity.Controller;

import com.example.LegoCity.Repository.BuildingTypeRepository;
import com.example.LegoCity.Service.CityService;
import com.example.LegoCity.Service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityWebController {

    private final CityService cityService;
    private final MissionService missionService;
    private final BuildingTypeRepository buildingTypes;

    @GetMapping
    public String city(Model model){
        model.addAttribute("city", cityService.getCityState());
        model.addAttribute("buildings", cityService.getAllBuildings());
        model.addAttribute("missions", missionService.getAllMissions());
        model.addAttribute("types", buildingTypes.findAll());
        return "city";
    }

    @PostMapping("/build")
    public String build(@RequestParam Long id){
        cityService.build(id);
        return "redirect:/city";
    }

    @PostMapping("/destroy")
    public String destroy(@RequestParam Long id){
        cityService.destroy(id);
        return "redirect:/city";
    }
}
