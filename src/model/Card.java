package model;

import controller.Controller;

import java.util.ArrayList;

public abstract class Card implements Cloneable{
    public String getName() {
        return name;
    }

    int attackArea;
    int numOfCardInCollection = 0;

    private String name;
    String description = " ";

    private String cardIdInGame;
    ArrayList<Buff> activatedBuffs = new ArrayList<>();
    boolean sold = false;
    private Block currentBlock;
    boolean owned = false;
    boolean stunned = false;
    boolean disarmed = false;
    boolean attackedThisTurn = false;
    int price;
    int Mp;
    int mp = this.Mp;
    int Hp;
    private String cardID;
    boolean isCardinGame = false;
    int healthLevel = this.Hp;
    int Ap;
    private boolean using = false;
    TypeOfCard typeOfAttack;
    public Card clone() throws CloneNotSupportedException{
        return (Card) super.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setCurrentBlock(Block currentBlock) {
        this.currentBlock = currentBlock;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setAp(int ap) {
        Ap = ap;
    }

    public void setHp(int healthLevel) {
        this.healthLevel = healthLevel;
        this.Hp = healthLevel;
    }

    public void call(Card card) {
        card.using = true;
    }

    public int getMp() {
        return this.mp;
    }

    public int getHealthLevel() {
        return healthLevel;
    }

    public int getAttackPower() {
        return Ap;
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

    public static Card returnCardById(String id) {
        Shop shop = Shop.getInstance();
        for (Card card : shop.getAllCards()) {
            if (card.cardID.equals(id)) {
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

    public String getCardID() {
        return this.cardID;
    }

    public ArrayList<Buff> getActivatedBuffs() {
        return activatedBuffs;
    }

    public void removeDeactivatedBuffs(Buff buff) {
        activatedBuffs.remove(buff);
    }

    public void addActivatedBuff(Buff buff) throws CloneNotSupportedException {
        Buff buffToBeAdded = buff.clone();
        activatedBuffs.add(buffToBeAdded);
        buff.setStartTurn(Controller.currentAccount.game.getTurn());
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    public String getCardIdInGame() {
        return cardIdInGame;
    }

    public abstract void printStats(int i);

    public abstract void attack(Card card);
}
