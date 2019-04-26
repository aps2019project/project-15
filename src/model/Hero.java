package model;

import java.util.ArrayList;

public class Hero extends Card {
    static ArrayList<Hero> heroes = new ArrayList<>();
    Buff buff;
    Item[][] itemCheck = new Item[4][];

    public void attack() {

    }

    private boolean hasFlag;

    private Flag myFlag;

    public void getFlag( Flag flag) {

    }

    public void keepFlag( Flag flag) {

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

}
