package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.min;


public class Minion extends Card {
    static ArrayList<Minion> minions = new ArrayList<>();
    Buff buff;
    TypeOfCounterAttack attackType;
    public String activationType;
    public String activationTime;
    int range;

    private boolean hasFlag = false;
    private Flag myFlag;

    public String id;

    public void setTypeOfAttack() {
        this.typeOfAttack = TypeOfCard.Minion;
    }

    public Minion(String id, int hp, int ap, int mp, String name, String activationTime, String attackType) {
        this.setCardID(id);
        this.setHp(hp);
        this.setAp(ap);
        this.setMp(mp);
        this.setTypeOfAttack();
        this.attackType = TypeOfCounterAttack.valueOf(attackType);
        this.id = id;
        this.description = this.activationType.toString();
        buff = new Buff(description);
        buff.card = this;
    }

    public void getFlag(Flag flag) {

    }

    public Buff getSpecialPower() {
        return this.buff;
    }


    public void attack(Card card) throws CloneNotSupportedException {
        if (this.getName().equalsIgnoreCase("Giv")) {
            return;
            //todo add give to other attacks
        }
        if (this.getName().equalsIgnoreCase("Ashkbos")) {
            if (card.getAttackPower() < this.Ap) {
                return;
            }
        }
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (this.activationType.equals(SpecialPowerActivation.onAttack.toString())) {
                this.buff.setActivated(true);
                specialPowerActing(card);
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.canCounterAttack(this)) {
                    minion.counterAttack(this);
                }
                if (minion.activationType.equals(SpecialPowerActivation.onDeath.toString())) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) card;
                hero.counterAttack(this);
            }
        }
    }

    public void specialPowerActing(Card card) throws CloneNotSupportedException{
        if (this.getName().equalsIgnoreCase("GhooleDosar")) {
            ghooledosar(card);
        }
        this.buff.setActivated(true);
        try {
            this.buff.buffEffect(card);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void ghooleTakCheshm(Card card) throws CloneNotSupportedException {
        this.buff.setActivated(true);
        this.buff.buffEffect(card);
    }

    private void ghooledosar(Card card) {
        for (Buff buff : card.getActivatedBuffs()) {
            if (buff.getType().equals(TypesOfBuff.holy) || buff.getType().equals(TypesOfBuff.power)) {
                card.removeDeactivatedBuffs(buff);
                buff.buffNotEffective(card);
            }
        }
    }

    public void counterAttack(Card card) throws CloneNotSupportedException {
        if (this.isInRange(card)) {
            card.healthLevel -= this.Ap;
            if (card.healthLevel < 0) {
                card.healthLevel = 0;
            }
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.activationType.equals(SpecialPowerActivation.onDeath.toString())) {
                    if (minion.healthLevel <= 0) {
                        minion.specialPowerActing(this);
                    }
                }
                if (minion.activationType.equals(SpecialPowerActivation.onDefend.toString())) {
                    minion.specialPowerActing(this);
                }
            }
        }
    }

    public boolean canCounterAttack(Card card) {
        if (this.attackType.equals(TypeOfCounterAttack.hybrid)) {
            return isInRange(card);
        }
        boolean isNeighbor = this.isInNeighborBlocks(card);
        boolean isInRange = this.isInRange(card);
        if (this.attackType.equals(TypeOfCounterAttack.melee)) {
            if (isNeighbor && isInRange) {
                return true;
            }
            return false;
        }
        if (this.attackType.equals(TypeOfCounterAttack.ranged)) {
            if (isInRange && !isNeighbor) {
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean isInRange(Card card) {
        return abs(this.getCurrentBlock().x - card.getCurrentBlock().x) + abs(this.getCurrentBlock().y - card.getCurrentBlock().y) <= range;
    }

    private boolean isInNeighborBlocks(Card card) {
        return abs(card.getCurrentBlock().y - this.getCurrentBlock().y) + abs(card.getCurrentBlock().y - this.getCurrentBlock().y) == 1;
    }

    public static void comboAttack(Card enemyCard, Minion[] minions) throws CloneNotSupportedException {
        int reduction = 0;
        for (int i = 0; i < minions.length; i++) {
            reduction += minions[i].Ap;
        }
        enemyCard.Hp -= reduction;
        if (enemyCard.Hp < 0) {
            enemyCard.Hp = 0;
        }
        if (enemyCard.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            Hero hero = (Hero) enemyCard;
            hero.counterAttack(minions[0]);
        } else if (enemyCard.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            Minion minion = (Minion) enemyCard;
            minion.counterAttack(minions[0]);
        }
    }

    public void keepFlag(Flag flag) {

    }

    public boolean getHasFlag() {
        return hasFlag;
    }

    public Flag getMyFlag() {
        return myFlag;
    }

    public static ArrayList<Minion> getMinions() {
        return minions;
    }

    public TypeOfCounterAttack getMinionType() {
        return attackType;
    }

    @Override
    public void printStats(int i) {
        View.getInstance().printMinionStats(this, i);
    }

    @Override
    public String toString() {
        String info = "name:" + this.getName() + "\n" + "id: " + this.id + "\n" + "price: " + this.price + "\n" + "Mp: " + this.mp + "\n" + "Hp:" + this.Hp + "\n" + "Ap:" + this.Ap + "\n" + "attackType: " + this.attackType + "\n" + "range: " + this.range + "\n" + "activationType: " + this.activationType + "\n" + "activationTime: " + this.activationTime + "\n";
        return info;
    }

    public int getRange() {
        return this.range;
    }

    public boolean hasComboAbility() {
        if (this.activationTime.equals(SpecialPowerActivation.combo.toString())) {
            return true;
        }
        return false;
    }

    public ArrayList<Card> cardsAttacked(Map map, Block block) {
        ArrayList<Card> returns = new ArrayList<>();
        if (this.getName().equalsIgnoreCase("GhooleTakCheshm")) {
            return getCardsOfGhooleTakCheshm(map, block, returns);
        }
        if (this.getName().equalsIgnoreCase("marGhoolpeikar")) {
            return marGhoolpeikarCards(map, block, returns);
        }
        if (this.getName().equalsIgnoreCase("Jadogar") || this.getName().equalsIgnoreCase("JadogarAzam")) {
            return jadogarReturns(map, block, returns);
        }
        if (this.getName().equalsIgnoreCase("Jen")) {
            return jenCards(returns);
        }
        if (this.getName().equalsIgnoreCase("NaneSarma")) {
            return naneSarmaCards(map, block, returns);
        }
        returns.add(block.getCard());
        return returns;
    }

    private ArrayList<Card> naneSarmaCards(Map map, Block block, ArrayList<Card> returns) {
        Account enemy = new Account();
        if (Controller.currentAccount.getGame().getActiveAccount().equals(Controller.currentAccount)) {
            enemy = Controller.enemyAccount;
        } else {
            enemy = Controller.currentAccount;
        }
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                Block block1 = map.getBlock(block.x + i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            enemy.getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.getX() - i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            enemy.getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.x - i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            enemy.getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.x + i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            enemy.getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
            }
        }
        return returns;
    }

    private ArrayList<Card> jenCards(ArrayList<Card> returns) {
        for (Card card : Controller.currentAccount.game.getActiveAccount().getMainDeck().getCards()) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                returns.add(card);
            }
        }
        return returns;
    }

    private ArrayList<Card> jadogarReturns(Map map, Block block, ArrayList<Card> returns) {
        returns.add(this);
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j <= 1; j++) {
                Block block1 = map.getBlock(block.x + i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            Controller.currentAccount.game.getActiveAccount().getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.getX() - i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            Controller.currentAccount.game.getActiveAccount().getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.x - i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            Controller.currentAccount.game.getActiveAccount().getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
                block1 = map.getBlock(block.x + i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    if (block1.getCard().getTypeOfAttack().equals(TypeOfCard.Minion) &&
                            Controller.currentAccount.game.getActiveAccount().getCardsInGame().contains(block1.card)) {
                        returns.add(block.getCard());
                    }
                }
            }
        }
        return returns;
    }

    private ArrayList<Card> marGhoolpeikarCards(Map map, Block block, ArrayList<Card> returns) {
        for (int i = 0; i <= 2; i++) {
            for (int j = 2 - i; j >= 0; j--) {
                Block block1 = map.getBlock(block.x + i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    returns.add(block.getCard());
                }
                block1 = map.getBlock(block.getX() - i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    returns.add(block.getCard());
                }
                block1 = map.getBlock(block.x - i, block.y + j);
                if (block1 != null && block1.getCard() != null) {
                    returns.add(block.getCard());
                }
                block1 = map.getBlock(block.x + i, block.y - j);
                if (block1 != null && block1.getCard() != null) {
                    returns.add(block.getCard());
                }
            }
        }
        return returns;
    }

    private ArrayList<Card> getCardsOfGhooleTakCheshm(Map map, Block block, ArrayList<Card> returns) {
        Block block1 = map.getBlock(block.x, block.y - 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x - 1, block.y - 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x - 1, block.y);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x - 1, block.y + 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x + 1, block.y + 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x + 1, block.y);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x + 1, block.y - 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        block1 = map.getBlock(block.x, block.y + 1);
        if (block1 != null && block1.getCard() != null) {
            returns.add(block.getCard());
        }
        return returns;
    }
}