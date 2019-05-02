package model;

class Block {
    private boolean poison;
    private boolean fire;
    private boolean holy;
    private boolean isEmpty;
    Card card;

    int x;
    int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
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
}
