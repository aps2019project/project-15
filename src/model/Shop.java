package model;

import controller.Controller;
import controller.DataCenter;
import model.menu.AccountMenu;
import model.menu.MainMenu;
import view.Request;
import view.View;

import controller.Controller;
import model.menu.Menu;
import view.Request;
import view.View;

import java.util.ArrayList;
import java.util.Objects;


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

    }

    public void show() {
        view.showSpells();
        view.showHeroesInShop();
    }

    public void search(String name) {

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
