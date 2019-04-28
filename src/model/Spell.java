package model;

import view.View;

import java.util.ArrayList;

public class Spell extends Card {
    static ArrayList<Spell> spells = new ArrayList<>();
    Buff buff;


    public Spell(int hp) {
        this.setHp(hp);
    }

    public void addToHp(int number) {
        this.healthLevel += number;
    }

    public void effect(Card card) {
        this.buff.buffEffect(card);
    }

    public static ArrayList<Spell> getSpells() {
        return spells;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printSpellStats(this, i);
    }
}
