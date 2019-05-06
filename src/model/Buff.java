package model;

import controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Buff implements Cloneable{
    private boolean activated = false;
    private int startTurn;
    private int duration;
    Card card;
    private int unit;


    private TypesOfBuff type;

    private boolean hpEffected = false;
    private boolean apEffected = false;
    public Buff clone() throws CloneNotSupportedException{
        return (Buff) super.clone();
    }

    public TypesOfBuff getType() {
        return type;
    }

    public boolean isActivated() {
        return activated;
    }

    public boolean isHpEffected() {
        return hpEffected;
    }

    public boolean isApEffected() {
        return apEffected;
    }

    public Buff(String description) {
        type = getTypeFromDesc(description);
        unit = getUnitsFromDesc(description);
        if (description.contains("hp")) {
            hpEffected = true;
        }
        if (description.contains("ap")) {
            apEffected = true;
        }
        duration = getDurationFromDesc(description);
    }

    public void makeBuff(String description) {

    }

    private int getDurationFromDesc(String description) {
        Pattern pattern = Pattern.compile("(\\d+) turn");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        if (description.contains("rest of the game")) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    private int getUnitsFromDesc(String description) {
        Pattern pattern = Pattern.compile("(\\d+) units");
        Matcher matcher = pattern.matcher(description);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        if (description.contains("infinite")) {
            return Integer.MAX_VALUE;
        }
        return 1;
    }

    private TypesOfBuff getTypeFromDesc(String description) {
        if (description != null && !description.equals("")) {
            if (description.contains("holy")) {
                return TypesOfBuff.holy;
            }
            if (description.contains("power")) {
                return TypesOfBuff.power;
            }
            if (description.contains("poison")) {
                return TypesOfBuff.poison;
            }
            if (description.contains("weakness")) {
                return TypesOfBuff.weakness;
            }
            if (description.contains("stun")) {
                return TypesOfBuff.stun;
            }
            if (description.contains("disarm")) {
                return TypesOfBuff.disarm;
            }
        }
        return TypesOfBuff.specialCase;
    }

    public int getStartTurn() {
        return startTurn;
    }

    public int getDuration() {
        return duration;
    }


    public boolean isStillActivated() {
        return Controller.currentAccount.game.getTurn() <= startTurn + duration;
    }

    public void effect() {

    }

    //todo a buff being activated for a card
    public void buffEffect(Card card) throws CloneNotSupportedException {
        if(this.card.getName().equalsIgnoreCase("gorg")){
            startTurn += 1;
        }
        if (this.card.getName().equalsIgnoreCase("HealthWithProfit")) {
            this.duration = 0;
        }
        if (this.card.getName().equalsIgnoreCase("sacrifice")) {
            unit = this.card.Hp;
        }
        if (this.card.getName().equalsIgnoreCase("GorgeSefid")) {
            gorgeSefidEffect();
        }
        if (card.getName().equalsIgnoreCase("Piran")) {
            if (this.type.equals(TypesOfBuff.poison)) {
                return;
            }
        }
        if (this.card.getName().equalsIgnoreCase("ShireDarande")) {
            shireDarande(card);
        }
        if(this.activated) {
            switch (this.type) {
                case holy:
                    if (card.attackedThisTurn) {
                        card.Hp += this.unit;
                    }
                    break;
                case power:
                    if (hpEffected) {
                        card.Hp += this.unit;
                    }
                    if (apEffected) {
                        card.Ap += this.unit;
                    }
                    if (this.card.getName().equalsIgnoreCase("Madness")) {
                        madnessEffectOnSelf();
                    }
                    if (this.card.getName().equalsIgnoreCase("Sacrifice")) {
                        sacrificeEffectOnSelf();
                    }
                    break;
                case poison:
                    card.Hp -= this.unit;
                    break;
                case weakness:
                    if (this.card.getName().equalsIgnoreCase("PahlevaneFars")) {
                        if (card.activatedBuffs.contains(this)) {
                            this.unit = 5;
                        }
                    }
                    if (hpEffected) {
                        card.Hp -= this.unit;
                    }
                    if (apEffected) {
                        card.Ap -= this.unit;
                    }
                    if (this.card.getName().equalsIgnoreCase("HealthWithProfit")) {
                        healthWithProfitEffectOnSlf();
                    }
                    break;
                case stun:
                    card.stunned = true;
                    break;
                case disarm:
                    card.disarmed = true;
                    if (card.getName().equalsIgnoreCase("GgorazeVahshi")) {
                        card.disarmed = false;
                    }
                    break;
                case specialCase:
                    if (this.card.getName().equalsIgnoreCase("AreaDispel")) {
                        areaDispelEffect();
                    } else if (this.card.getName().equalsIgnoreCase("Dispel") || this.card.getName().equalsIgnoreCase("Afsane")) {
                        dispelEffect(card);
                    } else if (this.card.getName().equalsIgnoreCase("Jadogar")) {
                        jadogarEffect(card);
                    } else if (this.card.getName().equalsIgnoreCase("JadgareAzam")) {
                        jadogareAzamEffect(card);
                    }
            }
            if (this.card.getName().equalsIgnoreCase("JasoseTorani") && this.startTurn == Controller.currentAccount.game.getTurn()) {
                jasoseTorani(card);
            }
        }
    }

    private void shireDarande(Card card) {
        for(Buff buff : card.getActivatedBuffs()){
            if(buff.getType().equals(TypesOfBuff.holy)){
                card.attackedThisTurn = false;
            }
        }
    }

    private void jasoseTorani(Card card) throws CloneNotSupportedException {
        Buff buff = new Buff("disarms the enemy for 1 turn");
        card.addActivatedBuff(buff);
        buff.buffEffect(card);
    }

    private void jadogareAzamEffect(Card card) {
        card.Ap += 2;
        card.Hp++;
    }

    private void jadogarEffect(Card card) {
        card.Ap += 2;
        card.healthLevel -= 1;
    }

    private void gorgeSefidEffect() {
        if (Controller.currentAccount.game.getTurn() == this.startTurn + 1) {
            this.card.healthLevel -= 6;
        } else if (Controller.currentAccount.game.getTurn() == this.startTurn + 2) {
            this.card.healthLevel -= 4;
        }
        if (this.card.healthLevel < 0) {
            this.card.healthLevel = 0;
        }
    }

    public static void buffEffectReversed() {
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (!buff.isStillActivated()) {
                    buff.activated = false;
                    card.removeDeactivatedBuffs(buff);
                    buff.buffNotEffective(card);
                }
            }
        }
        for (Card card : Controller.enemyAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (!buff.isStillActivated()) {
                    buff.activated = false;
                    card.removeDeactivatedBuffs(buff);
                    buff.buffNotEffective(card);
                }
            }
        }
    }

    public void buffNotEffective(Card card) {
        switch (this.type) {
            case disarm:
                card.disarmed = false;
                break;
            case stun:
                card.stunned = false;
        }
    }

    private void areaDispelEffect() {
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (buff.type.equals(TypesOfBuff.disarm) || buff.type.equals(TypesOfBuff.poison) ||
                        buff.type.equals(TypesOfBuff.weakness) || buff.type.equals(TypesOfBuff.stun)) {
                    buff.activated = false;
                    card.removeDeactivatedBuffs(buff);
                }
            }
        }
        for (Card card : Controller.enemyAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (buff.type.equals(TypesOfBuff.power) || buff.type.equals(TypesOfBuff.holy)) {
                    buff.activated = false;
                    card.removeDeactivatedBuffs(buff);
                }
            }
        }
    }

    private void dispelEffect(Card enemyCard) {
        for (Buff buff : this.card.activatedBuffs) {
            if (buff.type.equals(TypesOfBuff.disarm) || buff.type.equals(TypesOfBuff.poison) ||
                    buff.type.equals(TypesOfBuff.weakness) || buff.type.equals(TypesOfBuff.stun)) {
                buff.activated = false;
                card.removeDeactivatedBuffs(buff);
            }
        }
        for (Buff buff : enemyCard.activatedBuffs) {
            if (buff.type.equals(TypesOfBuff.power) || buff.type.equals(TypesOfBuff.holy)) {
                buff.activated = false;
                enemyCard.removeDeactivatedBuffs(buff);
            }
        }
    }

    private void healthWithProfitEffectOnSlf() throws CloneNotSupportedException {
        Buff buff = new Buff("Has 2 Holy buffs");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }

    private void madnessEffectOnSelf() throws CloneNotSupportedException {
        Buff buff = new Buff("disarm");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }

    private void sacrificeEffectOnSelf() throws CloneNotSupportedException {
        Buff buff = new Buff("weakness buff with infinite hp decrease");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public void setStartTurn(int startTurn) {
        this.startTurn = startTurn;
    }
}


