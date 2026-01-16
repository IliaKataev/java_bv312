package com.example.LegoCity.Controller;

import com.example.LegoCity.Repository.BuildingTypeRepository;
import com.example.LegoCity.Service.CityService;
import com.example.LegoCity.Service.MissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CityWebController {

    private final CityService cityService;
    private final MissionService missionService;
    private final BuildingTypeRepository buildingTypes;

    @GetMapping("/")
    public String city(Model model){
        model.addAttribute("city", cityService.getCityState());
        model.addAttribute("buildings", cityService.getAllBuildings());
        model.addAttribute("missions", missionService.getAllMissions());
        model.addAttribute("types", buildingTypes.findAll());

        return "city";
    }

    @PostMapping("/build/{id}")
    public String build(@PathVariable("id") Long typeId){
        cityService.build(typeId);
        return "redirect:/city";
    }

    @PostMapping("/destroy")
    public String destroy(@RequestParam Long typeId){
        cityService.destroy(typeId);
        return "redirect:/city";
    }
}
