package com.example.LegoCity.Service;

import com.example.LegoCity.Models.CityState;
import com.example.LegoCity.Models.Mission;
import com.example.LegoCity.Models.MissionRequirements;
import com.example.LegoCity.Repository.BuildingRepository;
import com.example.LegoCity.Repository.CityStateRepository;
import com.example.LegoCity.Repository.MissionRepository;
import com.example.LegoCity.Repository.MissionRequirementsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRequirementsRepository MRS;
    private final MissionRepository MR;
    private final CityStateRepository CSR;

    private final BuildingRepository buildingRepository;
    private final HistoryService HS;

    public void checkMission(){
        List<Mission> missions = MR.findAll();

        for (Mission mission : missions){
            if(mission.isCompleted()){
                continue;
            }
            boolean completed = isMissionCompleted(mission);
            if(completed){
                completeMission(mission);
            }
        }
    }

    private void completeMission(Mission mission) {
        mission.setCompleted(true);
        MR.save(mission);

        CityState city = CSR.findAll().get(0);
        city.setCubes(city.getCubes() + mission.getRewardCubes());
        city.setMaxBuildings(city.getMaxBuildings() + mission.getRewardMaxBuildings());

        CSR.save(city);

        HS.log("MISSION", "Миссия выполена. Награда: + " + mission.getRewardCubes() + " & " +
                mission.getRewardMaxBuildings());
    }

    private boolean isMissionCompleted(Mission mission){
        List<MissionRequirements> requirements = (List<MissionRequirements>)MRS.findByMission(mission);

        for(MissionRequirements req : requirements){
            long count = buildingRepository.countByType(req.getBuildingType());

            if (count < req.getRequiredCount()) {
                return false;
            }
        }
        return true;
    }

    public List<Mission> getAllMissions() {
        return MR.findAll();
    }
}
