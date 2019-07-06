package model;

import controller.Controller;

import java.util.ArrayList;

public class Item implements Cloneable {

    String itemName;
    public String id;
    private String desc = "";
    String price;
    String range;

    private Buff buff;

    public Item() {
        buff = new Buff(desc);
    }

    public Buff getBuff() {
        return buff;
    }

    public Item clone() throws CloneNotSupportedException {
        return (Item) super.clone();
    }

    public static Item getItemByName(String name) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    ArrayList<Card> itemEffectOnWhat(int x, int y) {
        ArrayList<Card> returns = new ArrayList<>();
        if (this.getItemName().equalsIgnoreCase("Namosesepar")) {
            returns.add(Controller.currentGame.getActiveAccount().getMainDeck().getDeckHero());
            return returns;
        }
        returns.add(Controller.currentGame.getMap().getBlock(x, y).getCard());
        return returns;
    }

    void itemEffect(Card card) throws CloneNotSupportedException {
        if (this.getItemName().equalsIgnoreCase("SheckHammer")) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero) || !card.attackedThisTurn) {
                return;
            }
        }
        if (this.getItemName().equalsIgnoreCase("KamaneDamol")) {
            KamaneDamo(card);
            return;
        }
        if (this.getItemName().equalsIgnoreCase("MajoneMana")) {
            MajoneMana(card);
            return;
        }
        if (this.getItemName().equalsIgnoreCase("TireDoshakh")) {
            tireDoShakh(card);
            return;
        }
        this.buff.buffEffect(card);
    }

    private void tireDoShakh(Card card) throws CloneNotSupportedException {
        if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            Minion minion = (Minion) card;
            if (!minion.getMinionType().equals(TypeOfCounterAttack.melee)) {
                buff.buffEffect(card);
            }
        } else if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            Hero hero = (Hero) card;
            if (!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)) buff.buffEffect(card);
        }
    }

    void tajeDanaii(String username) {
        if (Controller.currentGame.getTurn() < 4) {
            if (username.equalsIgnoreCase(Controller.currentAccount.getUsername())) {
                Controller.currentGame.addPlayerOneMp(1);
            } else {
                Controller.currentGame.addPlayerTwoMp(1);
            }
        }
    }

    void shamshireChini(String username) {
        if (username.equalsIgnoreCase(Controller.currentAccount.getUsername())) {
            shamshirechiniFunction();
            return;
        }
        shamshirechiniFunction();
    }

    private void shamshirechiniFunction() {
        for (Card card : Controller.currentAccount.getMainDeck().getCards()) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                Minion minion = (Minion) card;
                if (minion.getMinionType().equals(TypeOfCounterAttack.melee)) {
                    minion.Ap += 5;
                }
            } else if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) card;
                if (hero.getCounterAttack().equals(TypeOfCounterAttack.melee)) {
                    hero.Ap += 5;
                }
            }
        }
    }

    private void MajoneMana(Card card) {
        if (this.buff.getStartTurn() + 1 == Controller.currentGame.getTurn()) {
            if (Controller.currentAccount.getCardsInGame().contains(card)) {
                Controller.currentGame.addPlayerOneMp(3);
            } else {
                Controller.currentGame.addPlayerTwoMp(3);
            }
        }
    }

    private void KamaneDamo(Card card) {
        for (Card lookingForHero : Controller.currentAccount.getCardsInGame()) {
            if (lookingForHero.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) lookingForHero;
                if (!hero.getCounterAttack().equals(TypeOfCounterAttack.hybrid)) {
                    try {
                        this.buff.buffEffect(card);
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void pareSimorgh(String username) {
        if (username.equalsIgnoreCase(Controller.currentAccount.getUsername())) {
            for (Card card : Controller.enemyAccount.getCardsInGame()) {
                if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                    Hero hero = (Hero) card;
                    if (!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)) {
                        hero.Ap -= 2;
                        if (hero.Ap < 0) {
                            hero.Ap = 0;
                        }
                    }
                }
            }
            return;
        }
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                Hero hero = (Hero) card;
                if (!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)) {
                    hero.Ap -= 2;
                    if (hero.Ap < 0) {
                        hero.Ap = 0;
                    }
                }
            }
        }
    }

    void KingWisdom(String username) {
        if (username.equalsIgnoreCase(Controller.currentAccount.getUsername())) {
            Controller.currentGame.addPlayerOneMp(1);
        } else {
            Controller.currentGame.addPlayerTwoMp(1);
        }
    }

    void assassinationDaggerEffect() {
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                card.Hp--;
            }
        }
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }


    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    static ArrayList<Item> items = new ArrayList<>();

    public String getId() {
        return id;
    }


    public static Item getItemById(String id) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }


    public static ArrayList<Item> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "itemName: " + this.getItemName() + "\n" + "ID: " + this.getId() + "\n" + "price: " + this.getPrice() + "\n" + "desc: " + this.getDesc() + "\n";
    }
}
