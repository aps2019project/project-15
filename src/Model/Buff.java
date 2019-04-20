package Model;

enum TypesOfBuff {
    Holy , Power , Poison , weakness , stun , disarm
}


public class Buff {
    boolean activated = false;
    private int startTurn;
    private int duration;
    TypesOfBuff type;

    public int getStartTurn() {
        return startTurn;
    }

    public int getDuration() {
        return duration;
    }

    public void attack() {

    }

    public boolean isStillActivated(int turn) {
        return turn < startTurn + duration - 1;
    }

    public void effect() {

    }
}


