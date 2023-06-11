package fr.eg23.wargame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Deployment implements Serializable {
    private Map<Zone, List<Soldier>> deploymentMap;

    public Deployment() {
        deploymentMap = new HashMap<>();
    }

    public void addSoldier(Zone zone, Soldier soldier) {
        List<Soldier> soldiers = deploymentMap.getOrDefault(zone, new ArrayList<>());
        soldiers.add(soldier);
        deploymentMap.put(zone, soldiers);
    }

    public void addSoldier(Zone zone, List<Soldier> soldiers) {
        deploymentMap.put(zone, soldiers);
    }

    public List<Soldier> getSoldiersByArea(Zone zone) {
        return deploymentMap.getOrDefault(zone, new ArrayList<>());
    }

    public void removeSoldier(Soldier soldier, Zone zone){
        List<Soldier> soldiers = deploymentMap.getOrDefault(zone, new ArrayList<>());
        soldiers.remove(soldier);
        deploymentMap.put(zone, soldiers);

    }

    public int getDeployedZones(){
        return deploymentMap.size();
    }

    public int getDeployedNumByArea(Zone zone){
        return deploymentMap.getOrDefault(zone, new ArrayList<>()).size();
    }
    public void clearZone(Zone zone){
        deploymentMap.remove(zone);
    }
    public boolean isAllDeployed(){
        if (deploymentMap.size() != 5){
            return false;
        }
        for(List<Soldier> soldiers : deploymentMap.values()){
            if (soldiers.size() == 0){
                return false;
            }
        }
        return true;
    }
}
