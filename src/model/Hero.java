package model;

import view.View;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Hero extends Card {

    static ArrayList<Hero> heroes = new ArrayList<>();

    public String id;

    private int range;
    private Buff buff;

    private int coolDown = 0;
    private TypeOfCounterAttack counterAttack;
    Item[][] itemCheck = new Item[4][];
    private int manaSpecialPower;

    public void attack(Card card) throws CloneNotSupportedException {
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.canCounterAttack(this)) {
                    minion.counterAttack(this);
                }
                if (minion.activationType.equals(SpecialPowerActivation.onDeath.toString())) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) card;
                if (hero.canCounterAttack(this)) {
                    hero.counterAttack(card);
                }
            }
        }
    }

    public void counterAttack(Card card) throws CloneNotSupportedException{
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.activationTime.equals(SpecialPowerActivation.onDeath.toString())) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
                if (minion.activationTime.equals(SpecialPowerActivation.onDefend.toString())) {
                    minion.specialPowerActing(this);
                }
            }
        }
    }

    public boolean canCounterAttack(Card card) {
        if (this.counterAttack.equals(TypeOfCounterAttack.hybrid)) {
            return isInRange(card);
        }
        boolean isNeighbor = this.isInNeighborBlocks(card);
        boolean isInRange = this.isInRange(card);
        if (this.counterAttack.equals(TypeOfCounterAttack.melee)) {
            if (isNeighbor && isInRange) {
                return true;
            }
            return false;
        }
        if (this.counterAttack.equals(TypeOfCounterAttack.ranged)) {
            return isInRange && !isNeighbor;
        }
        return false;
    }

    private boolean isInRange(Card card) {
        if (this.range == 0) {
            return true;
        }
        return abs(this.getCurrentBlock().x - card.getCurrentBlock().x) + abs(this.getCurrentBlock().y - card.getCurrentBlock().y) <= range;
    }

    private boolean isInNeighborBlocks(Card card) {
        return abs(card.getCurrentBlock().y - this.getCurrentBlock().y) + abs(card.getCurrentBlock().y - this.getCurrentBlock().y) == 1;
    }

    public Hero() {
        this.setTypeOfAttack();
        buff = new Buff(description);
        buff.card = this;
    }

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Hero;
    }

    private boolean hasFlag = false;

    private Flag myFlag;

    public void getFlag(Flag flag) {
        hasFlag = true;
        flag.card = this;
    }

    public String getId() {
        return id;
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
        return "name: " + this.getName() + "\n" + "description: " + this.description + "\n" + "price: " + this.price + "\n" + "HP: " + this.Hp + "\n" + "AP: " + this.getAttackPower() + "\n" + "typeOfAttack: " + this.counterAttack + "\n" + "attackArea: " + this.attackArea + "\n" + "Mp: " + this.manaSpecialPower + "\n" + "coolDown: " + this.coolDown + "\n";
    }

    public TypeOfCounterAttack getCounterAttack() {
        return counterAttack;
    }
}
