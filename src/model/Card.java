package model;

import java.util.ArrayList;

enum TypeOfCard {
    Minion, Spell, Hero
}

public abstract class Card {
    public String getCardName() {
        return cardName;
    }

    static ArrayList<Card> cards ;
    private String cardName;
    String desc;
    int cardID;
    int cardIdInGame;
    private int x;
    private int y;
    boolean owned = false;
    boolean stunned = false;
    boolean disarmed = false;
    int price;

    private boolean using = false;
    TypeOfCard type;
    int Mp;
    int Hp;
    int Ap;


    public static Card returnCardName(String name) {
        for(Card item : cards) {
            if (item.cardName.equals(name)) {
                return item;
            }
        }
        return null;
    }

    public void call(Card card) {
        card.using = true;
    }

    public int getMp() {
        return Mp;
    }

    public int getHp() {
        return Hp;
    }

    public int getAp() {
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

    public Card returnCardByName(String name) {
        for (Card card  : Shop.allCards ) {
            if (card.cardName.equals(name)) {
                return card;
            }
        }
        return null;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public TypeOfCard getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    public int getCardID() {
        return cardID;
    }
}
