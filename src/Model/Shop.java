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
        System.out.println("To exit , please type 'exit' ");
        System.out.println("To show collection , please type 'show collection' ");
        System.out.println("To search an item in collection , please type 'search collection [item name]' ");
        System.out.println("To search a card in collection , please type 'search collection [card name]' ");
        System.out.println("To buy an item from shop , please type 'buy [item name]' ");
        System.out.println("To buy a card from shop , please type 'buy [card name]' ");
        System.out.println("To sell your card , please type 'sell [card id]' ");
        System.out.println("To sell your item , please type 'sell [item id]' ");
        System.out.println("To show all cards and items in shop , please type 'show' ");

    }

    public void sell(String name) {

    }

    public boolean isSold(String name) {
        return true;
    }

    public void showMenu() {

    }

}
