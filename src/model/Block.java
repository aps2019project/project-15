package model;

import controller.Controller;

import java.util.Random;

public class Block {
    private boolean poison = false;
    private boolean fire = false;
    private boolean holy = false;
    private boolean isEmpty = true;
    int startTurn = -5;
    Card card;
    Collectible collectible;
    Flag flag;

    int x;
    int y;

    Block(int x, int y) {
        this.x = x;
        this.y = y;
        int m = new Random().nextInt(10);
        if (m == 4) {
            poison = true;
            return;
        }
        if (m == 7) {
            fire = true;
            return;
        }
        if (m == 6) {
            holy = true;
        }
    }

    boolean isEmpty() {
        return isEmpty;
    }

    public boolean isHoly() {
        return holy;
    }

    private boolean isFire() {
        return fire;
    }

    private boolean isPoison() {
        return poison;
    }

    public void movedFromBlock() {
        this.card = null;
        this.isEmpty = true;
    }

    void cardMovedToBlock(Card card) {
        this.card = card;
        this.isEmpty = false;
    }

    void blockEffect() {
        if (this.startTurn != -5) {
            if (startTurn + 3 <= Controller.currentGame.getTurn()) {
                this.holy = false;
            }
        }
        if (this.card != null) {
            if (isFire()) {
                this.card.Hp -= 2;
                if (this.card.Hp < 0) {
                    this.card.Hp = 0;
                }
                return;
            }
            if (isHoly()) {
                if (this.card.attackedThisTurn) {
                    this.card.Hp++;
                }
                return;
            }
            if (isPoison()) {
                this.card.Hp--;
            }
        }
    }

    void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Card getCard() {
        return card;
    }

    void kaveEffect() {
        this.holy = true;
        startTurn = Controller.currentGame.getTurn();
    }

    public Collectible getCollectible() {
        return collectible;
    }

    public void setCollectible(Collectible collectible) {
        this.collectible = collectible;
    }

    void moveCollectible() {
        this.collectible = null;
    }

    void setFlag(Flag flag) {
        this.flag = flag;
    }

    void removeFlag() {
        this.flag = null;
    }
}
