package com.example.LegoCity.Service;

import com.example.LegoCity.Models.*;
import com.example.LegoCity.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springdoc.webmvc.core.service.RequestService;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionService {

    private final MissionRequirementsRepository MRS;
    private final MissionRepository MR;
    private final CityStateRepository CSR;
    private final UserRepository UR;
    private final BuildingTypeRepository BTR;

    private final BuildingRepository buildingRepository;
    private final HistoryService HS;

    private final RequestService requestBuilder;

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

        CityState city = CSR.findAll().getFirst();
        city.setCubes(city.getCubes() + mission.getRewardCubes());
        city.setMaxBuildings(city.getMaxBuildings() + mission.getRewardMaxBuildings());

        CSR.save(city);

        HS.log("MISSION", "Миссия выполена. Награда: + " + mission.getRewardCubes() + " & " +
                mission.getRewardMaxBuildings());
    }

    private boolean isMissionCompleted(Mission mission){
        List<MissionRequirements> requirements = MRS.findAllByMission(mission);

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

    public void resetAllUserMissions() {
        List<User> users = UR.findAll();
        for (User user : users){
            user.getMissions().clear();

            List<Mission> newMissions = generateRandomMissions(user);

            for(Mission mission : newMissions){
                mission.setUser(user);
            }

            user.getMissions().addAll(newMissions);
        }
    }

    private List<Mission> generateRandomMissions(User user) {
        List<Mission> missions = new ArrayList<>();
        for(int i = 0; i <= 4; i++){
            Mission mission = new Mission();
            mission.setCompleted(false);
            mission.setRewardCubes(random(5,20));
            mission.setRewardMaxBuildings(random(1,3));
            mission.setUser(user);

            List<MissionRequirements> requirements = generateRequirements(mission);

            mission.setRequirementsList(requirements);
            missions.add(mission);
        }
        return missions;
    }

    private List<MissionRequirements> generateRequirements(Mission mission) {
        List<BuildingType> types = BTR.findAll();
        Collections.shuffle(types);

        int requirementsCount = random(1,3);
        List<MissionRequirements> result = new ArrayList<>();

        for(int i = 0; i < requirementsCount; i++){
            MissionRequirements req = new MissionRequirements();
            req.setMission(mission);
            req.setBuildingType(types.get(i));
            req.setRequiredCount(random(1,5));
            result.add(req);
        }
        return result;
    }

    private int random(int min, int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
