package model;

import view.View;

import java.util.ArrayList;

public class Hero extends Card {
    static ArrayList<Hero> heroes = new ArrayList<>();
    int attackArea = 0;
    Buff buff;
    int coolDown = 0;
    TypeOfCounterAttack counterAttack;
    Item[][] itemCheck = new Item[4][];

    public void attack() {

    }

    private boolean hasFlag = false;

    private Flag myFlag;

    public void getFlag( Flag flag) {
        hasFlag = true;
        flag.card = this;
    }

    public void keepFlag( Flag flag) {
        flag.setCurrentBlock(this.getCurrentBlock());
    }
    public boolean getHasFlag() {
        return hasFlag;
    }

    public Flag getMyFlag() {
        return myFlag;
    }

    public static ArrayList<Hero> getHeroes() {
        return heroes;
    }

    @Override
    public TypeOfCard getTypeOfAttack() {
        return super.getTypeOfAttack();
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printHeroStats(this, i);
    }

    @Override
    public String toString() {
        String information = "name: " + this.getName() + "\n" + "description: " + this.description + "\n" + "price: " + this.price + "\n" + "HP: " + this.healthLevel + "\n" + "AP: " + this.attackPower + "\n" + "typeOfAttack: " + this.typeOfAttack + "\n" + "attackArea: " + this.attackArea + "\n" + "Mp: " + this.Mp + "\n" + "coolDown: " + this.coolDown + "\n";
        return information;
    }
}
