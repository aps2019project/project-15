package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;


public class Minion extends Card {
    static ArrayList<Minion> minions = new ArrayList<>();
    Buff buff;
    TypeOfCounterAttack attackType;
    SpecialPowerActivation activationType;
    SpecialPowerActivation activationTime;
    int range;

    private boolean hasFlag = false;
    private Flag myFlag;

    public String id;

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Minion;
    }

    public Minion(String id, int hp, int ap, int mp, String name, SpecialPowerActivation activationTime, TypeOfCounterAttack attackType) {
        this.setCardID(id);
        this.setHp(hp);
        this.setAp(ap);
        this.setMp(mp);
        this.setTypeOfAttack();
        this.activationTime = activationTime;
        this.attackType = attackType;
        this.id = id;
        this.description = String.valueOf(this.activationType);
        buff = new Buff(description);
        buff.card = this;
    }

    public void getFlag(Flag flag) {

    }

    public boolean specialPowerActivated() {
        return true;
    }

    public Buff getSpecialPower() {
        return this.buff;
    }


    public void attack(Card card) {
        if (this.getName().equalsIgnoreCase("Giv")) {
            return;
            //todo add give to other attacks
        }
        if (this.getName().equalsIgnoreCase("Ashkbos")) {
            if (card.getAttackPower() < this.Ap) {
                return;
            }
        }
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (this.activationType.equals(SpecialPowerActivation.onAttack)) {
                specialPowerActing(card);
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.canCounterAttack(this)) {
                    minion.counterAttack(this);
                }
                if (minion.activationType.equals(SpecialPowerActivation.onDeath)) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) card;
                hero.counterAttack(this);
            }
        }
    }

    public void specialPowerActing(Card card) {
        if (this.getName().equalsIgnoreCase("GhooleDosar")) {

        }
        this.buff.buffEffect(card);
    }

    private void ghooledosar(Card card) {
        for (Buff buff : card.getActivatedBuffs()) {
            if (buff.getType().equals(TypesOfBuff.holy) || buff.getType().equals(TypesOfBuff.power)) {
                card.removeDeactivatedBuffs(buff);
                buff.buffNotEffective(card);
            }
        }
    }

    public void counterAttack(Card card) {
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.activationType.equals(SpecialPowerActivation.onDeath)) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
                if (minion.activationType.equals(SpecialPowerActivation.onDefend)) {
                    minion.specialPowerActing(this);
                }
            }
        }
    }

    public boolean specialPowerActivation() {
        switch (this.activationTime) {
            case onRespawn:
                if (Controller.currentAccount.getCardsInGame().contains(this) || Controller.enemyAccount.getCardsInGame().contains(this)) {
                    return true;
                }
                break;
            case onAttack:
                //can only be called from attack
                return false;
            case Passive:
                return true;
            case onDeath:
                //can only be called from attack after hp turned to 0
                return false;
            case onDefend:
                //can only be called from counter attack
                return false;
        }
        return false;
    }

    public boolean canCounterAttack(Card card) {
        if (this.attackType.equals(TypeOfCounterAttack.hybrid)) {
            return isInRange(card);
        }
        boolean isNeighbor = this.isInNeighborBlocks(card);
        boolean isInRange = this.isInRange(card);
        if (this.attackType.equals(TypeOfCounterAttack.melee)) {
            if (isNeighbor && isInRange) {
                return true;
            }
            return false;
        }
        if (this.attackType.equals(TypeOfCounterAttack.ranged)) {
            if (isInRange && !isNeighbor) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isInRange(Card card) {
        return abs(this.getCurrentBlock().x - card.getCurrentBlock().x) + abs(this.getCurrentBlock().y - card.getCurrentBlock().y) <= range;
    }

    private boolean isInNeighborBlocks(Card card) {
        return abs(card.getCurrentBlock().y - this.getCurrentBlock().y) + abs(card.getCurrentBlock().y - this.getCurrentBlock().y) == 1;
    }

    public static void comboAttack(Card enemyCard, Minion[] minions) {
        int reduction = 0;
        for (int i = 0; i < minions.length; i++) {
            reduction += minions[i].Ap;
        }
        enemyCard.Hp -= reduction;
        if (enemyCard.Hp < 0) {
            enemyCard.Hp = 0;
        }
        if (enemyCard.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            Hero hero = (Hero) enemyCard;
            hero.counterAttack(minions[0]);
        } else if (enemyCard.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            Minion minion = (Minion) enemyCard;
            minion.counterAttack(minions[0]);
        }
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

    public SpecialPowerActivation getActivationTime() {
        return activationTime;
    }
}