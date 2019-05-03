package model;

import controller.Controller;
import model.menu.MainMenu;
import view.Request;
import view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection {

    private ArrayList<Card> myCards = new ArrayList<>();
    private ArrayList<Item> myItems = new ArrayList<>();
    private ArrayList<Deck> myDecks = new ArrayList<>();


    View view = View.getInstance();
    Request request = new Request();

    public void exitCollection() {
        view.exitCollection();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public void showCollection() { //todo
        view.heroStats();
        int i = 1;
        for (Card item : myCards) {
            if (item.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                item.printStats(i);
                i++;
            }
        }
        view.itemStats();
        i = 1;
        for (Item item : myItems) {
            view.printItemStats(item, i);
            i++;
        }
        i = 1;
        view.cardStats();
        for (Card item : myCards) {
            if (item.getTypeOfAttack().equals(TypeOfCard.Spell)) {
                item.printStats(i);
                i++;
            }
        }
        for (Card item : myCards) {
            if (item.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                item.printStats(i);
                i++;
            }
        }
    }

    boolean hasCard(Card card) {
        return this.getMyCards().contains(card);
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
        if (Controller.currentAccount.getMyCollection().getMyDecks() != null) {
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
        while (iter.hasNext()) {
            deck = iter.next();
            if (deck.getName().equals(name)) {
                iter.remove();
                return;
            }
        }
    }

    public void addCardToDeck(Card card, String deckName) {
        if (!myCards.contains(card)) {
            view.cardNotInCollection();
            return;
        }
        Deck deck = new Deck("");
        for (Deck deck1 : myDecks) {
            if (deck1.getName().equals(deckName)) {
                deck = deck1;
                if (deck1.getCards() != null && deck1.getCards().contains(card)) {
                    view.cardIsAlreadyInDeck();
                    return;
                }
            }
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return;
        }
        if (deck.numberOfCards() >= 2) {
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
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return;
        }
        if (deck.getItem() != null) {
            view.tooManyItems();
            return;
        }
        deck.addItem(item);

    }

    public void removeCardFromDeck(int cardID, String deckName) {
        Card card = Card.returnCardById(cardID);
        Deck deck = new Deck("");
        for (Deck deckItem : myDecks) {
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

    public void removeItemFromDeck(int id ,String deckName) {
        Item item = Item.getItemById(id);
        Deck deck = new Deck("");
        for (Deck deckItem : myDecks) {
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

    public void validateDeck(String deckName) {
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
        }
        else {
            deck.validateDeck();
        }
    }

    public void selectDeck(String deckName) {
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return;
        }
        if (!deck.validateDeck()) {
            view.deckIsNotValid();
            return;
        }
        Controller.currentAccount.setMainDeck(deck);
    }

    public void showAllDecks() {
        int i = 1;
        for (Deck deckItem : myDecks) {
            view.printDeckName(i, deckItem.getName());
            this.showDeck(deckItem.getName());
            i++;
        }
    }

    public void showDeck(String deckName) {
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return;
        }
        if (deck.cards == null) {
            System.out.println("no cards in this deck!");
            if (deck.item == null) {
                System.out.println("no items!");
                return;
            }
        }
        if (deck.cards != null) {
            int i = 1;
            for (Card item : deck.getCards()) {
                if (item.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                    item.printStats(i);
                    i++;
                }
            }
        }
        int i = 1;
        //view.printItemStats(deck.getItem(), i);
        view.showItemsInDeck(deckName);
        if (deck.cards != null) {
            i = 1;
            view.cardStats();
            for (Card item : deck.getCards()) {
                if (item.getTypeOfAttack().equals(TypeOfCard.Spell)) {
                    item.printStats(i);
                    i++;
                }
            }
            for (Card item : deck.getCards()) {
                if (item.getTypeOfAttack().equals(TypeOfCard.Minion)) {
                    item.printStats(i);
                    i++;
                }
            }
        }
    }

    public void help() {
        view.printCollectionHelpMenu();
    }

    public void save() {

    }

    private ArrayList<Card> getMyCards() {
        return myCards;
    }

    ArrayList<Item> getMyItems() {
        return myItems;
    }

    public ArrayList<Deck> getMyDecks() {
        return myDecks;
    }

    void addCardToCollection(Card card) {
        this.myCards.add(card);
    }


    boolean addItemToCollection(Item item) {
        if (myItems.size() >= 3) {
            view.tooManyItemsInCollection();
            return false;
        }
        this.myItems.add(item);
        return true;
    }

    Card findCardInCollection(Card card) {
        for (Card card1 : myCards) {
            if (card1 == card) {
                return card1;
            }
        }
        return null;
    }

    Item findItemInCollection(Item item) {
        for (Item item1 : myItems) {
            if (item1 == item) {
                return item1;
            }
        }
        return null;
    }

    void removeCardFromCollection(Card card) {
        myCards.remove(card);
    }

    void removeItemFromCollection(Item item) {
        myItems.remove(item);
    }

    public void cardOrItemToDeck(int id, String deckName) {
        Card card = Card.returnCardById(id);
        Item item = Item.getItemById(id);
        if (card != null) {
            System.out.println("it is card");
            addCardToDeck(card, deckName);
        } else if (item != null) {
            System.out.println("it is item");
            addItemToDeck(item, deckName);
        }
        System.out.println("oskol");
    }

    public void removeCardOrItemFromDeck(String group, String group1) {
        Card card = Card.returnCardByName(group);
        Item item = Item.getItemByName(group);
        if (card!= null) {
            this.removeCardFromDeck(Integer.parseInt(group), group1);

        }
        else if (item != null){
            this.removeItemFromDeck(Integer.parseInt(group), group1);
        }
    }
}
