package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Hero extends Card {

    static ArrayList<Hero> heroes = new ArrayList<>();
    int id;

    Buff buff;
    int coolDown = 0;
    TypeOfCounterAttack counterAttack;
    Item[][] itemCheck = new Item[4][];
    int manaSpecialPower;
    public void attack() {

    }

    public Hero() {
        this.setTypeOfAttack();
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

/*    public void keepFlag( Flag flag) {
        flag.setCurrentBlock(flag.getCurrentBlock());
    }*/

    public void death() {

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
        String information = "name: " + this.getName() + "\n" + "description: " + this.description + "\n" + "price: " + this.price + "\n" + "HP: " + this.Hp + "\n" + "AP: " + this.getAttackPower() + "\n" + "typeOfAttack: " + this.counterAttack + "\n" + "attackArea: " + this.attackArea + "\n" + "Mp: " + this.manaSpecialPower + "\n" + "coolDown: " + this.coolDown + "\n";
        return information;
    }

    public void diveSefid() {

    }

    public void simorgh() {
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            card.stunned = true;
        }
    }

    public void attack(Card card) {

    }

    private void Arash(Card card) {
    }

    private void Afsane(Card card) {
    }
}
