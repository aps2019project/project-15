package model;

import view.View;

import java.util.ArrayList;

import static java.lang.Math.abs;


public class Minion extends Card {
    static ArrayList<Minion> minions = new ArrayList<>();
    Spell specialPower;
    int range ;
    SpecialPowerActivation activationType;
    TypeOfCounterAttack attackType;
    String activationTime;


    private boolean hasFlag = false;
    private Flag myFlag;

    public int id;

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Minion;
    }

    public Minion(int id, int hp, int ap, int mp, String name, SpecialPowerActivation activationType, TypeOfCounterAttack attackType) {
        this.setCardID(id);
        this.setHp(hp);
        this.setAp(ap);
        this.setMp(mp);
        this.setTypeOfAttack();
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

    public void attack(Card cards) {

    }
    public boolean canCounterAttack(Card card){
        if(this.attackType.equals(TypeOfCounterAttack.hybrid)){
       return isInRange(card);
        }
        boolean isNeighbor = this.isInNeighborBlocks(card);
        boolean isInRange = this.isInRange(card);
        if(this.attackType.equals(TypeOfCounterAttack.melee)){
            if(isNeighbor && isInRange){
                return true;
            }
            return false;
        }
        if(this.attackType.equals(TypeOfCounterAttack.ranged)){
            if(isInRange && !isNeighbor){
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean isInRange(Card card){
        return abs(this.getCurrentBlock().x - card.getCurrentBlock().x) + abs(this.getCurrentBlock().y - card.getCurrentBlock().y) <= range;
    }
    private boolean isInNeighborBlocks(Card card){
        if(card.getCurrentBlock().x == this.getCurrentBlock().x && card.getCurrentBlock().y - this.getCurrentBlock().y == 1){
            return true;
        }
        if(card.getCurrentBlock().x == this.getCurrentBlock().x &&  this.getCurrentBlock().y - card.getCurrentBlock().y == 1){
            return true;
        }
        if(card.getCurrentBlock().y == this.getCurrentBlock().y && card.getCurrentBlock().x - this.getCurrentBlock().x == 1){
            return true;
        }
        if(card.getCurrentBlock().y == this.getCurrentBlock().y &&  this.getCurrentBlock().x - card.getCurrentBlock().x == 1){
            return true;
        }
        return false;
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