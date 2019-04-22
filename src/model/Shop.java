package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Shop {
    static ArrayList<Card> allCards;
    static ArrayList<Usable> allUsables;
    static ArrayList<Hero> allHeroes;

    private View view = View.getInstance();

    public void exitShop() {

    }

    public void showCollection() {

    }

    public int searchInShop(String name) {
        return 0;
    }

    public int searchInCollection(String name) {
        Collection myCollection = new Collection();
        for (Card card : allCards
        ) {
            if (card.getCardName().equals(name)) {
                return 1;
            }
        }
        return 0;
    }

    public boolean isBought(String name) {
        return true;
    }

    public void buy(String name) {

    }

    public void show() {

    }

    public void help() {
        view.printShopHelpMenu();
    }

    public void sell(String name) {

    }

    public boolean isSold(String name) {
        return true;
    }

    public void showMenu() {

    }

}