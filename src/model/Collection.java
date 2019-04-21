package model;

import controller.Controller;

import java.util.ArrayList;
import View.*;

class Collection {
    private ArrayList<Card> myCards;
    private ArrayList<Item> myItems;
    private ArrayList<Deck> myDecks;

    public void exitCollection() {

    }

    public void showCollection() {

    }

    public void searchInCollection(String cardNumber) {

    }

    public void createDeck(String name) {

    }

    public void deleteDeck(String name) {

    }

    public void addCardToDeck(int id, String deckName) {

    }

    public void addHeroToDeck(int id, String deckname) {

    }

    public void addItemToDeck(int id, String deckname) {

    }


    public void removeCardFromDeck(int id, String deckName) {

    }

    public void removeItemFromDeck(int id, String deckname) {

    }

    public void removeHeroFromDeck(int id, String deckname) {

    }

    public void showMenu() {

    }

    public boolean validateDeck(String deckName) {
        return true;
    }

    public boolean selectDeck(String deckName) {
        return true;
    }

    public void showAllDecks() {

    }

    public void showDeck(String deckName) {

    }

    public void help() {
        view.printCollectionHelpMenu();
    }

    public void save() {

    }

    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    public ArrayList<Item> getMyItems() {
        return myItems;
    }

    public ArrayList<Deck> getMyDecks() {
        return myDecks;
    }

    public void adCardToCollection(Card card) {

    }

    public void addItemToCollection(Item item) {

    }
}
