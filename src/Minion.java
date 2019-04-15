import java.util.ArrayList;

enum typeOfMinion {
    ranged, melee, hybrid
}

enum specialPowerActivation {
    onRespawn, Passive, onDeath, onAttack, onDefend
}

class Minion extends Card {

    Spell specialPower;
    String name;
    ArrayList<Block> range;
    specialPowerActivation type;
    typeOfMinion minionType;

    private boolean hasFlag = false;

    private Flag myFlag;

    public void getFlag(Flag flag) {

    }

    public boolean specialPowerActivated() {
        return true;
    }

    public Spell getSpecialPower() {
        return this.specialPower;
    }

    public void attack() {

    }

    public void keepFlag(Flag flag) {

    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public Flag getMyFlag() {
        return myFlag;
    }
}