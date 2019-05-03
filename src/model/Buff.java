package model;

import controller.Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum TypesOfBuff {
    holy, power, poison, weakness, stun, disarm, specialCase
}


public class Buff {
    private boolean activated = false;
    private int startTurn;
    private int duration;
    Card card;
    private int unit;


    private TypesOfBuff type;

    private boolean hpEffected = false;
    private boolean apEffected = false;

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
        if (this.card.getName().equalsIgnoreCase("sacrifice")) {
            unit = this.card.Hp;
        } else {
            unit = getUnitsFromDesc(description);
        }
        if (description.contains("hp")) {
            hpEffected = true;
        }
        if (description.contains("ap")) {
            apEffected = true;
        }
        duration = getDurationFromDesc(description);
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

    private int getDurationFromDesc(String description) {
        Pattern pattern = Pattern.compile("(\\d+) turn");
        Matcher matcher = pattern.matcher(description);
        if (this.card.getName().equalsIgnoreCase("HealthWithProfit")) {
            return 0;
        }
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        if (description.contains("rest of the game")) {
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    private TypesOfBuff getTypeFromDesc(String description) {
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
        return TypesOfBuff.specialCase;
    }

    public int getStartTurn() {
        return startTurn;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isStillActivated() {
        return Controller.currentAccount.game.getCurrentTurn() < startTurn + duration;
    }

    public void effect() {

    }

    //todo a buff being activated for a card
    public void buffEffect(Card card) {
        if (Controller.currentAccount.game.getCurrentTurn() == this.startTurn || this.type == TypesOfBuff.holy) {
            //todo check if they are still activated
            switch (this.type) {
                case holy:
                    //todo check if the card was attacked in last turn
                    card.Hp++;
                    break;
                case power:
                    if (hpEffected) {
                        card.Hp++;
                    }
                    if (apEffected) {
                        card.Ap++;
                    }
                    if (this.card.getName().equalsIgnoreCase("Madness")) {
                        madnessEffectOnSelf();
                    }
                    if (this.card.getName().equalsIgnoreCase("Sacrifice")) {
                        sacrificeEffectOnSelf();
                    }
                    break;
                case poison:
                    card.Hp--;
                    break;
                case weakness:
                    if (hpEffected) {
                        card.Hp--;
                    }
                    if (apEffected) {
                        card.Ap--;
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
                    break;
                case specialCase:
                    if (card.getName().equalsIgnoreCase("AreaDispel")) {
                        areaDispelEffect();
                    } else if (card.getName().equalsIgnoreCase("Dispel")) {
                        dispelEffect(card);
                    }
            }
        }
    }

    public static void buffEffectReversed() {
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (!buff.isStillActivated()) {
                    card.removeDeactivatedBuffs(buff);
                    buff.buffNotEffective(card);
                }
            }
        }
        for (Card card : Controller.enemyAccount.getMainDeck().getCards()) {
            for (Buff buff : card.activatedBuffs) {
                if (!buff.isStillActivated()) {
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

    private void healthWithProfitEffectOnSlf() {
        Buff buff = new Buff("Has 2 Holy buffs");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }

    private void madnessEffectOnSelf() {
        Buff buff = new Buff("disarm");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }

    private void sacrificeEffectOnSelf() {
        Buff buff = new Buff("weakness buff with infinite hp decrease");
        this.card.addActivatedBuff(buff);
        buff.buffEffect(this.card);
    }
}


