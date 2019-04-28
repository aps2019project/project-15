package model;

import controller.Controller;
import view.Request;
import view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection {
    private ArrayList<Card> myCards;
    private ArrayList<Item> myItems;
    private ArrayList<deck> myDecks;

    View view = View.getInstance();
    Request request = new Request();

    public void exitCollection() {

    }

    public void showCollection() {

    }

    public void searchInCollection() {
        String name = request.getNewCommand();
        Card card = Card.returnCardByName(name);
        if (!card.equals(null)) {
            view.printCardId(card);
            return;
        }
        Collectible collectible = Collectible.returnItemName(name);
        if (!collectible.equals(null)) {
            view.printCollectibleID(collectible);
            return;
        }
        view.notFoundInCollection();
    }

    public void createDeck(String name) {
        for (deck item : myDecks) {
            if (item.getName().equals(name)) {
                view.deckAlreadyExists();
                return;
            }
        }
        deck deck = new deck(name);
        myDecks.add(deck);
    }

    public void deleteDeck(String name) {
        Iterator<deck> iter = myDecks.iterator();
        deck deck = new deck("");
        while (iter.hasNext()) {
            deck = iter.next();
            if (deck.getName().equals(name)) {
                iter.remove();
                return;
            }
        }
    }

    public void addCardToDeck(String cardID ,String deckName) {
        Card card = Card.returnCardByName(cardID);
        if (card != null && !myCards.contains(card)) {
            view.cardNotInCollection();
            return;
        }
        deck deck = new deck("");
        for (deck item : myDecks) {
            if (item.getName().equals(deckName)) {
                deck = item;
                if (item.getCards().contains(card)) {
                    view.cardIsAlreadyInDeck();
                    return;
                }
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        if (deck.numberOfCards() >= 20) {
            view.tooManyCardsInDeck();
            return;
        }
        if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            for (Card item : deck.getCards()) {
                if (item.getTypeOfAttack().equals(TypeOfCard.Hero))
                    view.heroInDeck();
                return;
            }
        }
        deck.addCard(card);
    }

    public void addItemToDeck(Item item, String deckName) {
        if (!myItems.contains(item)) {
            view.itemNotInCollection();
            return;
        }
        deck deck = new deck("");
        for (deck deckItem : myDecks) {
            if (deckItem.getName().equals(deckName)) {
                deck = deckItem;
                if (deckItem.getItem().equals(item)) {
                    view.itemIsAlreadyInDeck();
                    return;
                }
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        if (!deck.getItem().equals(null)) {
            view.tooManyItems();
            return;
        }
        deck.addItem(item);
    }


    public void removeCardFromDeck(String cardID, String deckName) {
        Card card = Card.returnCardByName(cardID);
        deck deck = new deck("");
        for (deck deckItem : myDecks) {
            if (deckItem.getName().equals(deckName)) {
                deck = deckItem;
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        deck.deleteCard(card);
    }

    public void removeItemFromDeck(Item item, String deckName) {
        deck deck = new deck("");
        for (deck deckItem : myDecks) {
            if (deckItem.getName().equals(deckName)) {
                deck = deckItem;
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        deck.deleteItem(item);
    }

    public void showMenu() {

    }

    public boolean validateDeck(String deckName) {
        deck deck = new deck("");
        for (deck deckItem : myDecks) {
            if (deckItem.getName().equals(deckName)) {
                deck = deckItem;
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return false;
        }
        return deck.validateDeck();
    }

    public void selectDeck(String deckName) {
        deck deck = new deck("");
        for (deck deckItem : myDecks) {
            if (deckItem.getName().equals(deckName)) {
                deck = deckItem;
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        Controller.currentAccount.setMainDeck(deck);
    }

    public void showAllDecks() {
view.showAllDecks();
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

    public ArrayList<deck> getMyDecks() {
        return myDecks;
    }

    public void adCardToCollection(Card card) {
        this.myCards.add(card);
    }

    public boolean addItemToCollection(Item item) {
        if (myItems.size() >= 3) {
            view.tooManyItemsInCollection();
            return false;
        }
        this.myItems.add(item);
        return true;
    }
}
