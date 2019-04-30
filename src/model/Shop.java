package model;

import controller.Controller;
import model.menu.MainMenu;
import view.View;

import java.util.ArrayList;

public class Shop {
    static ArrayList<Card> allCards;
    static ArrayList<Usable> allUsables;
    static ArrayList<Hero> allHeroes;
    Collection collection = new Collection();
    private View view = View.getInstance();


    public void exitShop() {
        view.exitShop();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public void showCollection() {


    }

/*
    public int searchInShop(String name) {
       if(Card.returnCardByName(name)== null)
       {
           if(Card.returnCardItemName(name)==null);
           {
               //show this card dosen't exist
           }
               //show id item
       }
       //show id card(minion or spell or hero)

        return 0;

    }
*/

    public int searchInCollection(String name) {
        Collection myCollection = new Collection();
        for (Card card : allCards
        ) {
            if (card.getName().equals(name)) {
                return 1;
            }
        }
        return 0;
    }

    public boolean isBought(String name) {
        return true;
    }

    public void buy(String name) {
        Card card = Card.returnCardByName(name);
        Item item  = findItemByName(name);
        if(card.equals(null) && item.equals(null)){
            view.itemOrCardIsNotInShop();
            return;
        }
        if(!card.equals(null) && card.price > Controller.currentAccount.getMoney()){
            view.notEnoughMoney();
            return;
        }
        if(!item.equals(null) && Controller.currentAccount.getMyCollection().getMyItems().size() >= 3){
            view.tooManyItemsInMyCollection();
            return;
        }
        if(!card.equals(null)){
            Controller.currentAccount.reduceMoney(card.getPrice());
            Controller.currentAccount.getMyCollection().adCardToCollection(card);
            view.cardBought();
            return;
        }
        if(!item.equals(null)){
            Controller.currentAccount.reduceMoney(item.getPrice());
            Controller.currentAccount.getMyCollection().addItemToCollection(item);
            view.itemBought();
        }
    }

    public void show() {
        view.showMinions();
        view.showSpells();
        view.showHeroesInShop();
    }

    public void search(String name) {

    }

    public void help() {
        view.printShopHelpMenu();
    }

    public void sell(String name) {
        Card card = Controller.currentAccount.getMyCollection().findCardInCollection(name);
        Item item = Controller.currentAccount.getMyCollection().findItemInCollection(name);
        if(card.equals(null) && item.equals(null)){
            view.notOwnThisCardOrItem();
            return;
        }
        if(!card.equals(null)){
            Controller.currentAccount.getMyCollection().removeCardFromCollection(card);
            Controller.currentAccount.addMoney(card.getPrice());
            view.cardSold();
            return;
        }
        if(!item.equals(null)){
            Controller.currentAccount.getMyCollection().removeItemFromCollection(item);
            Controller.currentAccount.addMoney(item.getPrice());
            view.itemSold();
        }
    }

    public boolean isSold(String name) {


        return true;
    }

    public void showMenu() {

    }
    private Item findItemByName(String name){
        for(Item item : allUsables){
            if(item.getItemName().equals(name)){
                return item;
            }
        }
        return null;
    }
}
