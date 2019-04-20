package Model;

import Model.Card;

public class Flag extends Item {
    private int x;
    private int y;
    Card card;

    public Card condition() {
        return null;
    }

    public void flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
