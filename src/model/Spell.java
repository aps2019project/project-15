package model;

import view.View;

import java.util.ArrayList;

public class Spell extends Card {
    static ArrayList<Spell> spells = new ArrayList<>();
    String name;
    Buff buff;


    public void addToHp(int number) {

    }

    public void effect() {

    }
    public static ArrayList<Spell> getSpells(){
        return spells;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printSpellStats(this, i);
    }
}
