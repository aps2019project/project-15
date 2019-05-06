package model;

import controller.Controller;
import view.View;

import java.lang.Math;
import java.util.ArrayList;

public class Spell extends Card {
    private static ArrayList<Spell> spells = new ArrayList<>();
    private Buff buff;

    private String range;
    public String id;

    public Spell(int hp) {
        this.setHp(hp);
        buff = new Buff(this.description);
        buff.card = this;
        this.setTypeOfAttack();
    }

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Spell;
    }

    public void addToHp(int number) {
        this.healthLevel += number;
    }

    public void effect(Card card) throws CloneNotSupportedException{
        if (checkEffectiveness(card)) {
            this.buff.buffEffect(card);
        } else {
            View.getInstance().youCanNotAttackThisCard();
        }
    }

    public boolean checkEffectiveness(Card card) {
        if (this.getName().equalsIgnoreCase("AllAttack")) {
            return checkForAllAttack(card);
        }
        if (this.getName().equalsIgnoreCase("AreaDispel")) {
            //todo check to be part of a 2x2 block with other cards?
            return true;
        }
        if (this.getName().equalsIgnoreCase("Dispel")) {
            return true;
        }
        if (this.getName().equalsIgnoreCase("KingsGuard") || this.getName().equalsIgnoreCase("LightingBolt")) {
            return kingsGuardInRange(card) && isEnemy(card);
        }
        if (this.getName().equalsIgnoreCase("PoisonLake")) {
            //todo check the cards effected to be part of 3x3 square
            return true;
        }
        if (this.range.contains("enemy") && this.range.contains("hero")) {
            return isEnemy(card) && isHero(card);
        }
        if (this.range.contains("enemy") && this.range.contains("minion")) {
            return isEnemy(card) && isMinion(card);
        }
        if (this.range.contains("self") && this.range.contains("minion")) {
            return isEnemy(card) && isMinion(card);
        }
        if (this.range.contains("enemy")) {
            return isEnemy(card);
        }
        if (this.range.contains("self")) {
            return checkIfSelfPower(card);
        }
        return false;
    }

    private boolean kingsGuardInRange(Card card) {
        if (Math.abs((this.getCurrentBlock().x - card.getCurrentBlock().x)) == 1)
            return true;
        else if (Math.abs((this.getCurrentBlock().y - card.getCurrentBlock().y)) == 1)
            return true;
        return false;
    }

    private boolean isHero(Card card) {
        return card.getTypeOfAttack().equals(TypeOfCard.Hero);
    }

    private boolean isMinion(Card card) {
        return card.getTypeOfAttack().equals(TypeOfCard.Minion);
    }

    private boolean checkIfSelfPower(Card card) {
        if (Controller.currentAccount.getCardsInGame().contains(this)) {
            if (Controller.currentAccount.getCardsInGame().contains(card)) {
                return true;
            }
        } else if (Controller.enemyAccount.getCardsInGame().contains(this)) {
            if (Controller.enemyAccount.getCardsInGame().contains(card)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEnemy(Card card) {
        if (Controller.currentAccount.getCardsInGame().contains(this)) {
            if (Controller.currentAccount.getCardsInGame().contains(card)) {
                return false;
            }
        } else if (Controller.enemyAccount.getCardsInGame().contains(this)) {
            if (Controller.enemyAccount.getCardsInGame().contains(card)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkForAllAttack(Card card) {
        if (Controller.currentAccount.getCardsInGame().contains(this)) {
            if (Controller.currentAccount.getCardsInGame().contains(card)) {
                return false;
            }
        } else if (Controller.enemyAccount.getCardsInGame().contains(this)) {
            if (Controller.enemyAccount.getCardsInGame().contains(card)) {
                return false;
            }
        }
        if (this.getCurrentBlock().x != card.getCurrentBlock().x) {
            return false;
        }
        return true;
    }

    public static ArrayList<Spell> getSpells() {
        return spells;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printSpellStats(this, i);
    }

    public String toString() {
        String info = "name: " + this.getName() + "\n" + "price: " + this.price + "\n" + "Mp: " + this.mp + "\n" + "range: " + this.range + "\n" + "description: " + this.description + "\n";
        return info;
    }

    public void attack(Card card) throws CloneNotSupportedException{
        buff.buffEffect(card);
    }
}
