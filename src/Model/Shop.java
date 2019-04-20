package Model;

import java.util.ArrayList;

public class Shop {
    ArrayList<Card> allCards;
    ArrayList<Usable> allUsables;
    ArrayList<Hero> allHeroes;

    public void exitShop() {

    }

    public void showCollection() {

    }

    public int searchInShop(String name) {
        return 0;
    }

    public int searchInCollection(String name) {
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
        Control.view.printShopHelpMenu();
    }

    public void sell(String name) {

    }

    public boolean isSold(String name) {
        return true;
    }

    public void showMenu() {

    }

}
