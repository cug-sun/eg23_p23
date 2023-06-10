package fr.eg23.wargame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player implements Serializable {
    private String name;
    private int point;
    private int id;
    private List<Zone> occupation;
    private Deployment deployment;
    private List<Soldier> privates = new ArrayList<>();
    private List<Soldier> elites = new ArrayList<>();
    private List<Soldier> maitres = new ArrayList<>();
    private List<Soldier> allSoldiers = new ArrayList<>();

    public Player(String name, int playerId) {
        this.name = name;
        //pre-assign
        this.point = 0;
        this.id = playerId;
        //initialize soldiers
        for (int i = 0; i < 15; i++) {
            Soldier soldier = new Soldier(Rank.PRIVATE, i+1, playerId);
            privates.add(soldier);
        }
        for (int i = 0; i < 4; i++) {
            Soldier soldier = new Soldier(Rank.ELIITE, i+1, playerId);
            elites.add(soldier);
        }
        for (int i = 0; i < 1; i++) {
            Soldier soldier = new Soldier(Rank.MAITREDEGUERRE, i+1, playerId);
            maitres.add(soldier);
        }
        //set allSoldiers
        List<Soldier> soldiers = new ArrayList<>();
        soldiers.addAll(privates);
        soldiers.addAll(elites);
        soldiers.addAll(maitres);
        allSoldiers = soldiers;
        //set occupation
        occupation = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {

        this.point += point;
    }

    public List<Soldier> getPrivates() {
        return privates;
    }

    public List<Soldier> getElites() {
        return elites;
    }

    public List<Soldier> getMaitres() {
        return maitres;
    }

    public List<Soldier> getAllSoldiers() {
        return allSoldiers;
    }

    public void setAllSoldiers(List<Soldier> allSoldiers) {
        this.allSoldiers = allSoldiers;
    }

    public Deployment getDeployment() {
        return deployment;
    }

    public void setDeployment(Deployment deployment) {
        this.deployment = deployment;
    }

    public List<Zone> getOccupation() {
        return occupation;
    }

    public void setOccupation(List<Zone> occupation) {
        this.occupation = occupation;
    }
    public void removeSoldiers(List<Soldier> soldiers){
        for (Soldier soldier : soldiers) {
            allSoldiers.remove(soldier);
        }
    }
    public boolean hasEnoughReservists(){
        int sum = 0;
        for(Soldier soldier: allSoldiers){
            if (soldier.isReservist()){
                sum += 1;
            }
        }
        return sum == 5;
    }
}
