package model;

enum TypeOfCard {
    Minion, Spell, Hero
}

public abstract class Card {
    public String getName() {
        return name;
    }

    private String name;
    String description;
    int cardID;
    int cardIdInGame;
    private int x;
    private int y;
    boolean owned = false;
    boolean stunned = false;
    boolean disarmed = false;
    int price;

    private boolean using = false;
    TypeOfCard typeOfAttack;
    int Mp;
    int healthLevel;
    int attackPower;

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
        return attackPower;
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
        for (Card card : Shop.allCards) {
            if (card.name.equals(name)) {
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
        return cardID;
    }
    public void printStats(int i){ }
}
