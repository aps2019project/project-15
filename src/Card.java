enum TypeOfCard {
    Minion , Spell , Hero
}

class Card {
    int cardID;
    int cardIdInGame;
    private int x;
    private int y;
    private boolean using;
    TypeOfCard type;
    int Mp;
    int Hp;
    int Ap;

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

}
