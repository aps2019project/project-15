package Model;

import java.util.ArrayList;

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
        System.out.println("To exit from collection, type 'exit' ");
        System.out.println("To show all cards and items in your collection, type 'show' ");
        System.out.println("To search a card in collection, type 'search [card name]' ");
        System.out.println("To search an item in collection, type 'search [item name]' ");
        System.out.println("To save your changes, type 'save' ");
        System.out.println("To create deck, type 'create deck [deck name]' ");
        System.out.println("To add card to deck, type 'add [card id] to deck [deck name]' ");
        System.out.println("To add item to deck, type 'add [item id] to deck [deck name]' ");
        System.out.println("To add hero to deck, type 'add [hero id] to deck [deck name]' ");
        System.out.println("To remove a card from deck, type 'remove [card id] from [deck name]' ");
        System.out.println("To remove an item from deck, type 'remove [item id] from [deck name]' ");
        System.out.println("To remove a hero from deck, type 'remove [hero id] from [deck name]' ");
        System.out.println("To check validity of a deck, type 'validate deck [deck name]' ");
        System.out.println("To choose your main deck, type 'select deck [deck name]' ");
        System.out.println("To show your all decks, type 'show all decks' ");
        System.out.println("To show a deck, type 'show deck [deck name]' ");

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
