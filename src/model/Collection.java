package model;

import controller.Controller;
import model.menu.MainMenu;
import view.Request;
import view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection {

    private static Collection collection = new Collection();
    public static Collection getInstance() {
        return collection;
    }
    private ArrayList<Card> myCards = new ArrayList<>();
    private ArrayList<Item> myItems = new ArrayList<>();
    private ArrayList<Deck> myDecks = new ArrayList<>();

    View view = View.getInstance();
    Request request = new Request();
    public void exitCollection() {
        view.exitCollection();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public void showCollection() {
        view.heroStats();
        int i=1;
        for(Card item : myCards){
            if(item.getTypeOfAttack().equals(TypeOfCard.Hero)){
                item.printStats(i);
                i++;
            }
        }
        view.itemStats();
        i = 1;
        for(Item item : myItems){
            view.printItemStats(item, i);
            i++;
        }
        i = 1;
        view.cardStats();
        for(Card item : myCards){
            if(item.getTypeOfAttack().equals(TypeOfCard.Spell)){
                item.printStats(i);
                i++;
            }
        }
        for(Card item : myCards){
            if(item.getTypeOfAttack().equals(TypeOfCard.Minion)){
                item.printStats(i);
                i++;
            }
        }
    }

    public boolean hasCard(Card card) {
        if (this.getMyCards().contains(card)) return true;
        return false;
    }
    public void searchInCollection() {
        String name = request.getNewCommand();
        Card card = Card.returnCardByName(name);
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
        if (myDecks != null) {
            for (Deck item : myDecks) {
                if (item.getName().equals(name)) {
                    view.deckAlreadyExists();
                    return;
                }
            }
        }
        Deck deck = new Deck(name);
        myDecks.add(deck);
    }

    public void deleteDeck(String name) {
        Iterator<Deck> iter = myDecks.iterator();
        Deck deck = new Deck("");
        while (iter.hasNext()){
            deck = iter.next();
            if(deck.getName().equals(name)){
                iter.remove();
                return;
            }
        }
    }

    public void addCardToDeck(String cardID, String deckName) {
        Card card = Card.returnCardByName(cardID);
        if(!myCards.contains(card)){
            view.cardNotInCollection();
            return;
        }
        Deck deck = new Deck("");
        for(Deck item : myDecks){
            if(item.getName().equals(deckName)){
                deck = item;
                if(item.getCards().contains(card)){
                    view.cardIsAlreadyInDeck();
                    return;
                }
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        if(deck.numberOfCards() >= 20){
            view.tooManyCardsInDeck();
            return;
        }
        if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
            for(Card item : deck.getCards()){
                if(item.getTypeOfAttack().equals(TypeOfCard.Hero))
                    view.heroInDeck();
                return;
            }
        }
        deck.addCard(card);
    }

    public void addItemToDeck(Item item, String deckName) {
        if(!myItems.contains(item)){
            view.itemNotInCollection();
            return;
        }
        Deck deck = new Deck("");
        for(Deck deckItem : myDecks){
            if(deckItem.getName().equals(deckName)){
                deck = deckItem;
                if(deckItem.getItem().equals(item)){
                    view.itemIsAlreadyInDeck();
                    return;
                }
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        if(!deck.getItem().equals(null)){
            view.tooManyItems();
            return;
        }
        deck.addItem(item);
    }


    public void removeCardFromDeck(String cardID, String deckName) {
        Card card = Card.returnCardByName(cardID);
        Deck deck = new Deck("");
        for(Deck deckItem : myDecks){
            if(deckItem.getName().equals(deckName)){
                deck = deckItem;
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        deck.deleteCard(card);
    }

    public void removeItemFromDeck(Item item, String deckName) {
        Deck deck = new Deck("");
        for(Deck deckItem : myDecks){
            if(deckItem.getName().equals(deckName)){
                deck = deckItem;
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        deck.deleteItem(item);
    }

    public void showMenu() {

    }

    public boolean validateDeck(String deckName) {
        Deck deck = new Deck("");
        for(Deck deckItem : myDecks){
            if(deckItem.getName().equals(deckName)){
                deck = deckItem;
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return false;
        }
        return deck.validateDeck();
    }

    public void selectDeck(String deckName) {
        Deck deck = new Deck("");
        for(Deck deckItem : myDecks){
            if(deckItem.getName().equals(deckName)){
                deck = deckItem;
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        Controller.currentAccount.setMainDeck(deck);
    }

    public void showAllDecks() {
        int i = 1;
        for(Deck deckItem : myDecks){
            view.printDeckName(i, deckItem.getName());
            this.showDeck(deckItem.getName());
            i++;
        }
    }

    public void showDeck(String deckName) {
        Deck deck = new Deck("");
        for(Deck item : myDecks){
            if(item.getName().equals(deckName)){
                deck = item;
                break;
            }
        }
        if(deck.getName().equals("")){
            view.deckIsNotInCollection();
            return;
        }
        int i = 1;
        for(Card item : deck.getCards()){
            if(item.getTypeOfAttack().equals(TypeOfCard.Hero)){
                item.printStats(i);
                i++;
            }
        }
        view.itemStats();
        i = 1;
        view.printItemStats(deck.getItem(), i);
        i = 1;
        view.cardStats();
        for(Card item : deck.getCards()){
            if(item.getTypeOfAttack().equals(TypeOfCard.Spell)){
                item.printStats(i);
                i++;
            }
        }
        for(Card item : deck.getCards()){
            if(item.getTypeOfAttack().equals(TypeOfCard.Minion)){
                item.printStats(i);
                i++;
            }
        }
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
        this.myCards.add(card);
    }

    public boolean addItemToCollection(Item item) {
        if(myItems.size() >= 3){
            view.tooManyItemsInCollection();
            return false;
        }
        this.myItems.add(item);
        return true;
    }
    public Card findCardInCollection(String name){
        for(Card card : myCards){
            if(card.getName().equals(name)){
                return card;
            }
        }
        return null;
    }
    public Item findItemInCollection(String name){
        for(Item item : myItems){
            if(item.getItemName().equals(name)){
                return item;
            }
        }
        return null;
    }
    public void removeCardFromCollection(Card card){
        myCards.remove(card);
    }
    public void removeItemFromCollection(Item item){
        myItems.remove(item);
    }
}
