public class Flag extends Item {
    private int x;
    private int y;
    Card card;

    public Card condition() {

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
