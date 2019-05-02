package model;

import view.View;

import java.util.ArrayList;


public class Minion extends Card {
    static ArrayList<Minion> minions = new ArrayList<>();
    Spell specialPower;
    //ArrayList<Block> range = new ArrayList<>();
    SpecialPowerActivation activationType;
    TypeOfCounterAttack attackType;
    String activationTime;

    private boolean hasFlag = false;
    private Flag myFlag;

    public int id;

    public Minion(int id, int hp, int ap, int mp, String name, SpecialPowerActivation activationType, TypeOfCounterAttack attackType) {
        this.setCardID(id);
        this.setHp(hp);
        this.setAp(ap);
        this.setMp(mp);
        this.activationType = activationType;
        this.attackType = attackType;
        this.id = id;
    }


    public void setCardId() {
        this.setCardID(this.id);
        this.id = this.getCardID();
    }

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

    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    public TypeOfCounterAttack getMinionType() {
        return attackType;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printMinionStats(this, i);
    }

    @Override
    public String toString() {
        String info = "name:" + this.getName() + "\n" + "id: " + this.id + "\n" + "price: " + this.price + "\n" + "Mp: " + this.mp + "\n" + "Hp:" + this.Hp + "\n" + "Ap:" + this.Ap + "\n" + "attackType: " + this.attackType + "\n" + "range: " + this.range + "\n" + "activationType: " + this.activationType + "\n" + "activationTime: " + this.activationTime + "\n";
        return info;
    }
}