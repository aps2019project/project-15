package model;

import java.util.ArrayList;

enum TypeOfCard {
    Minion, Spell, Hero
}

public abstract class Card {
    public String getName() {
        return name;
    }

    int attackArea;

    private String name;
    String description;

    private int cardID;

    int cardIdInGame;
    ArrayList<Buff> activatedBuffs = new ArrayList<>();
    boolean sold = false;

    private int x;

    private int y;

    private Block currentBlock;
    boolean owned = false;
    boolean stunned = false;
    boolean disarmed = false;
    int price;
    int Mp;
    String range;
    int mp = this.Mp;
    int Hp;

    int healthLevel;
    int Ap;
    private boolean using = false;


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

   /* public void setX(int x) {
        this.x = x;
    }

    */
    /* public void setY(int y) {
         this.y = y;
     }

     */


    TypeOfCard typeOfAttack;

    public void setName(String name) {
        this.name = name;
    }

    public void setMp(int mp) {
        Mp = mp;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public void setAp(int ap) {
        Ap = ap;
    }

    public void setHp(int healthLevel) {
        this.healthLevel = healthLevel;
    }

    public void call(Card card) {
        card.using = true;
    }

    public int getMp() {
        return Mp;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getAttackPower() {
        return Ap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isUsing() {
        return using;
    }

    public void move() {

    }

    public static Card returnCardByName(String name) {
        Shop shop = Shop.getInstance();
        for (Card card : shop.allCards) {
            if (card.name.equalsIgnoreCase(name)) {
                return card;
            }
        }
        return null;
    }

    public static Card returnCardById(int id) {
        for (Card card : Shop.getInstance().getAllCards()) {
            if (card.cardID == id) {
                return card;
            }
        }
        return null;
    }

    public TypeOfCard getTypeOfAttack() {
        return typeOfAttack;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getCardID() {
        return this.cardID;
    }

    public ArrayList<Buff> getActivatedBuffs() {
        return activatedBuffs;
    }

    public void removeDiactivatedBuffs(Buff buff) {
        activatedBuffs.remove(buff);
    }
    public void addActivatedBuff(Buff buff){
        activatedBuffs.add(buff);
    }

    public abstract void printStats(int i);
}
