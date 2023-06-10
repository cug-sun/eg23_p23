package fr.eg23.wargame;


import androidx.annotation.NonNull;

import java.io.Serializable;

public class Soldier implements Serializable {
    private int id;
    private int hp = 30;
    private int playerId;
    private int force;
    private int dexterity;
    private int resistance;
    private int constitution;
    private int initiative;
    private boolean reservist;
    private AiType ai;
    public Rank rank;

    public Soldier(Rank rank, int id, int playerId){
        this.rank = rank;
        this.id = id;
        this.playerId = playerId;
        this.reservist = false;
        //average 400 point to 20 soldiers, 20 points/per, 4 points/per attribute
        switch (rank){
            case PRIVATE:
                this.setForce(4);
                this.setDexterity(4);
                this.setResistance(4);
                this.setConstitution(4);
                this.setInitiative(4);
                this.setAi(AiType.Defensive);
                break;
            case ELIITE:
                this.setForce(1+4);
                this.setDexterity(1+4);
                this.setResistance(1+4);
                this.setConstitution(5+4);
                this.setInitiative(1+4);
                this.setAi(AiType.Defensive);
                break;
            case MAITREDEGUERRE:
                this.setForce(2+4);
                this.setDexterity(2+4);
                this.setResistance(2+4);
                this.setConstitution(10+4);
                this.setInitiative(2+4);
                this.setAi(AiType.Defensive);
                break;
            default:
                break;
        }
    }

    @NonNull
    @Override
    public String toString() {
        String name = this.rank.toString() + this.id;
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getId() {
        return id;
    }

    public Rank getRank() {
        return rank;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public boolean isReservist() {
        return reservist;
    }

    public void setReservist(boolean reservist) {
        this.reservist = reservist;
    }

    public AiType getAi() {
        return ai;
    }

    public void setAi(AiType ai) {
        this.ai = ai;
    }

    public int getPlayerId() {
        return playerId;
    }
}
