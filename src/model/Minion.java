package model;

import view.View;

import java.util.ArrayList;

enum typeOfCounterAttack {
    ranged, melee, hybrid
}

enum specialPowerActivation {
    onRespawn, Passive, onDeath, onAttack, onDefend
}

public class Minion extends Card {
    static ArrayList<Minion> minions = new ArrayList<>();
    Spell specialPower;
    String name;
    ArrayList<Block> range;
    specialPowerActivation type;
    typeOfCounterAttack minionType;

    private boolean hasFlag = false;

    private Flag myFlag;

    public void getFlag(Flag flag) {

    }

    public boolean specialPowerActivated() {
        return true;
    }

    public Spell getSpecialPower() {
        return this.specialPower;
    }

    public void attack() {

    }

    public void keepFlag(Flag flag) {

    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public Flag getMyFlag() {
        return myFlag;
    }
    public static ArrayList<Minion> getMinions(){
        return minions;
    }

    public typeOfCounterAttack getMinionType() {
        return minionType;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printMinionStats(this, i);
    }
}