package com.example.LegoCity.Service;

import com.example.LegoCity.Models.Building;
import com.example.LegoCity.Models.BuildingType;
import com.example.LegoCity.Models.CityState;
import com.example.LegoCity.Repository.BuildingRepository;
import com.example.LegoCity.Repository.BuildingTypeRepository;
import com.example.LegoCity.Repository.CityHistoryRepository;
import com.example.LegoCity.Repository.CityStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {

    private final BuildingTypeRepository BTR;
    private final BuildingRepository BR;
    private final CityStateRepository CSR;
    private final HistoryService HS;
    private final MissionService MS;

    private final CityHistoryRepository cityHistoryRepository;

    public void build(Long buildingTypeId){

        CityState city = getCityState();

        BuildingType type = BTR.findById(buildingTypeId).orElseThrow(() -> new IllegalArgumentException("Тип здания не найден"));

        long totalBuildings = BR.count();

        if(totalBuildings >= city.getMaxBuildings()){
            throw new IllegalStateException("Достигнут лимит зданий");
        }

        if(city.getCubes() < type.getCubeCost()){
            throw new IllegalStateException("Недостаточно кубиков");
        }

        city.setCubes(city.getCubes() - type.getCubeCost());
        CSR.save(city);

        Building building = new Building();
        building.setType(type);
        building.setBuiltAt(LocalDateTime.now());
        BR.save(building);

        HS.log("BUILD", "Построено здание: " + type.getName());
        MS.checkMission();
    }

    public void resetCity(){
        BR.deleteAll();

        CityState city = getCityState();
        city.setCubes(50);
        city.setMaxBuildings(5);
        CSR.save(city);

        HS.log("RESET", "Город сброшен до начального состояния");

    }

    public void addCubes(int amount){
        CityState city = getCityState();
        city.setCubes(city.getCubes() + amount);
        CSR.save(city);

        HS.log("EVENT", "Получено кубиков: " + amount);
    }

    public CityState getCityState(){

        return CSR.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("CityState не найден"));
    }

    public List<Building> getAllBuildings() {
        return BR.findAll();
    }

    public void destroy(Long buildingId) {
        Building building = BR.findById(buildingId).orElseThrow(() -> new IllegalArgumentException("Здание не найдено"));

        CityState city = getCityState();

        int refund = building.getType().getCubeCost() / 2;
        city.setCubes(city.getCubes() + refund);

        BR.delete(building);
        CSR.save(city);

        HS.log("DESTROY", building.getType().getName() + ". Refund: " + refund);
    }
    //Для каждой из моделек нужно сделать геттер и сеттер + Join в некоторых
}
