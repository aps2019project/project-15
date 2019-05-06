package model;

public class Block {
    private boolean poison = false;
    private boolean fire = false;
    private boolean holy = false;
    private boolean isEmpty = true;
    Card card;

    int x;
    int y;

    Block(int x, int y) {
        this.x = x;
        this.y = y;
        int m = (int) Math.random();
        m %= 10;
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

    public Card cardContain() {
        return null;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean isHoly() {
        return holy;
    }

    public boolean isFire() {
        return fire;
    }

    public boolean isPoison() {
        return poison;
    }

    public void movedFromBlock() {
        this.card = null;
        this.isEmpty = true;
    }

    public void cardMoverdToBlock(Card card) {
        this.card = card;
        this.isEmpty = false;
    }

    public void blockEffect() {
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

    public void setEmpty(boolean empty) {
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
}
