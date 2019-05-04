package model;

import controller.Controller;

import java.util.ArrayList;

public class Item {

    String itemName;
    public String id;
    String desc;
    String price;
    String range;
    Buff buff;

    public Item() {
        buff = new Buff(desc);
    }

    public void itemEffect(Card card){
        //todo check ghosleTamid to be called after entering a card
        //todo check that shock hammer is called after hero is attacked
        if(this.getItemName().equalsIgnoreCase("KamaneDamol")){
            KamaneDamo(card);
            return;
        }
        if(this.getItemName().equalsIgnoreCase("MajoneMana")){
            MajoneMana(card);
            return;
        }
        if(this.getItemName().equalsIgnoreCase("TireDoshakh")){
            tireDoShakh(card);
            return;
        }
        this.buff.buffEffect(card);
    }

    private void tireDoShakh(Card card) {
        if(card.getTypeOfAttack().equals(TypeOfCard.Minion)){
            Minion minion = (Minion) card;
            if(!minion.getMinionType().equals(TypeOfCounterAttack.melee)){
                buff.buffEffect(card);
            }
        }
        else if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
            Hero hero = (Hero) card;
            if(!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)){
                buff.buffEffect(card);
            }
        }
    }

    public void tajeDanaii(String username){
        if(Controller.currentAccount.game.getTurn() < 4){
            if(username.equalsIgnoreCase(Controller.currentAccount.getUsername())){
                Controller.currentAccount.game.addPlayerOneMp(1);
            }
            else {
                Controller.currentAccount.game.addPlayerTwoMp(1);
            }
        }
    }
    public void shamshireChini(String username){
        if(username.equalsIgnoreCase(Controller.currentAccount.getUsername())){
            for(Card card : Controller.currentAccount.getMainDeck().getCards()){
                if(card.getTypeOfAttack().equals(TypeOfCard.Minion)){
                    Minion minion = (Minion) card;
                    if(minion.getMinionType().equals(TypeOfCounterAttack.melee)){
                        minion.Ap+=5;
                    }
                }
                else if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
                    Hero hero = (Hero) card;
                    if(hero.getCounterAttack().equals(TypeOfCounterAttack.melee)){
                        hero.Ap += 5;
                    }
                }
            }
            return;
        }
        for(Card card : Controller.currentAccount.getMainDeck().getCards()){
            if(card.getTypeOfAttack().equals(TypeOfCard.Minion)){
                Minion minion = (Minion) card;
                if(minion.getMinionType().equals(TypeOfCounterAttack.melee)){
                    minion.Ap+=5;
                }
            }
            else if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
                Hero hero = (Hero) card;
                if(hero.getCounterAttack().equals(TypeOfCounterAttack.melee)){
                    hero.Ap += 5;
                }
            }
        }
    }

    private void MajoneMana(Card card) {
        if(this.buff.getStartTurn() + 1 == Controller.currentAccount.game.getTurn()){
            if(Controller.currentAccount.getCardsInGame().contains(card)){
                Controller.currentAccount.game.addPlayerOneMp(3);
            }
            else{
                Controller.currentAccount.game.addPlayerTwoMp(3);
            }
        }
    }

    private void KamaneDamo(Card card) {
        for(Card lookingForHero : Controller.currentAccount.getCardsInGame()){
            if(lookingForHero.getTypeOfAttack().equals(TypeOfCard.Hero)){
                Hero hero = (Hero) lookingForHero;
                if(!hero.getCounterAttack().equals(TypeOfCounterAttack.hybrid)){
                    this.buff.buffEffect(card);
                }
            }
        }
    }
    public void pareSimorgh(String username){
        if(username.equalsIgnoreCase(Controller.currentAccount.getUsername())){
            for(Card card : Controller.enemyAccount.getCardsInGame()){
                if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
                    Hero hero = (Hero) card;
                    if(!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)){
                        hero.Ap -= 2;
                        if(hero.Ap < 0){
                            hero.Ap = 0;
                        }
                    }
                }
            }
            return;
        }
        for(Card card : Controller.currentAccount.getCardsInGame()){
            if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
                Hero hero = (Hero) card;
                if(!hero.getCounterAttack().equals(TypeOfCounterAttack.melee)){
                    hero.Ap -= 2;
                    if(hero.Ap < 0){
                        hero.Ap = 0;
                    }
                }
            }
        }
    }
    public void KingWisdom(String username){
        //shuld be called each turn
        if(username.equalsIgnoreCase(Controller.currentAccount.getUsername())){
            Controller.currentAccount.game.addPlayerOneMp(1);
        }
        else {
            Controller.enemyAccount.game.addPlayerTwoMp(1);
        }
    }

    public void assassinationDaggerEffect(){
        //todo check to be called only after entering a card to game
        for(Card card : Controller.enemyAccount.getCardsInGame()){
            if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
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


    public int getX() {
        return x;
    }

    int x;

    public int getY() {
        return y;
    }

    int y;

    static Item getItemById(String id) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    static Item getItemByName(String name) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean isCollected() {
        return collected;
    }

    boolean collected;

    public static ArrayList<Item> getItems() {
        return items;
    }

    boolean using = false;


    public static void addToItems(Item item) {
        items.add(item);
    }

    public boolean isUsing() {
        return using;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }

    public String returnItemName() {
        if (items.contains(this)) {
            return this.itemName;
        }
        return null;
    }

    public void showCollectables() {
        //graphics to be added
    }

    public void selectCollectable(String collectableId) {

    }

    public void showInfo() {

    }

    public void use(int x, int y) {

    }

    @Override
    public String toString() {
        String info = "itemName: " + this.getItemName() + "\n" + "ID: " + this.getId() + "\n" + "price: " + this.getPrice() + "\n" + "desc: " + this.getDesc() + "\n";
        return info;
    }
}
