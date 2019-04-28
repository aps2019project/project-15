package model;

enum TypesOfBuff {
    Holy, Power, Poison, weakness, stun, disarm
}


public class Buff {
    boolean activated = false;
    private int startTurn;
    private int duration;
    TypesOfBuff type;
    boolean hpEffected = false;
    boolean apEffected = false;

    public int getStartTurn() {
        return startTurn;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isStillActivated(int turn) {
        return turn < startTurn + duration - 1;
    }

    public void effect() {

    }

    //todo a buff being activated for a card
    public void buffEffect(Card card) {
        //todo check if they are still activated
        switch (this.type) {
            case Holy:
                //todo check if the card was attacked in last turn
                card.healthLevel++;
                break;
            case Power:
                if (hpEffected) {
                    card.healthLevel++;
                }
                if (apEffected) {
                    card.attackPower++;
                }
                break;
            case Poison:
                card.healthLevel--;
                break;
            case weakness:
                if (hpEffected) {
                    card.healthLevel--;
                }
                if (apEffected) {
                    card.attackPower--;
                }
                break;
            case stun:
                card.stunned = true;
                break;
            case disarm:
                card.disarmed = true;
        }
    }
}


