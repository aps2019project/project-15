package model;

import view.Request;
import view.View;

import java.util.ArrayList;

class Collection {
    private ArrayList<Card> myCards;
    private ArrayList<Item> myItems;
    private ArrayList<Deck> myDecks;

    View view = View.getInstance();
    Request request = new Request();
    public void exitCollection() {

    }

    public void showCollection() {
        view.heroDeclarationInCollection();
        view.itemDeclarationInCollection();
        view.minionAndSpellDeclarationInCollection();
    }

    public void searchInCollection() {
        String name = request.getNewCommand();
        Card card = Card.returnCardName(name);
        if(!card.equals(null)){
            view.printCardId(card);
            return;
        }
        Collectible collectible = Collectible.returnItemName(name);
        if(!collectible.equals(null)){
            view.printCollectibleID(collectible);
            return;
        }
        view.notFoundInCollection();
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
