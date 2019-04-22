package model;

import java.util.ArrayList;

enum TypeOfCard {
    Minion, Spell, Hero
}

public abstract class Card {
    public String getCardName() {
        return cardName;
    }

    ArrayList<Card> cards;
    private String cardName;
    int cardID;
    int cardIdInGame;
    private int x;
    private int y;
    boolean stuned = false;
    boolean disarmed = false;

    private boolean using = false;
    TypeOfCard type;
    int Mp;
    int Hp;
    int Ap;


    public String returnCardName() {
        if (cards.contains(this)) {
            return this.cardName;
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

}