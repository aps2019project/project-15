package model;

import controller.Controller;
import menu.MainMenu;
import view.View;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection {

    private ArrayList<Card> myCards = new ArrayList<>();
    private ArrayList<Item> myItems = new ArrayList<>();
    private ArrayList<Deck> myDecks = new ArrayList<>();
    View view = View.getInstance();

    public void exitCollection() {
        view.exitCollection();
        Controller.currentMenu = MainMenu.getInstance();
    }

    public ArrayList<Card> myCards() {
        return myCards;
    }

    public ArrayList<Item> myItems() {
        return myItems;
    }

    public ArrayList<Deck> myDecks() {
        return myDecks;
    }

    public void showCollection() {
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
        return this.myCards().contains(card);
    }

    public Object searchInCollection(String name) {
        Card card = Card.returnCardByName(name);
        if (card != null) {
            view.printCardId(card);
            return card;
        }
        Item item = Item.getItemByName(name);
        if (item != null) {
            view.printItemID(item);
            return item;
        }
        Item collectible = Collectible.returnItemName(name);
        if (collectible != null) {
            view.printCollectibleID(collectible);
            return collectible;
        }
        view.notFoundInCollection();
        return null;
    }

    public boolean createDeck(String name) {
        if (Controller.currentAccount.getMyCollection().myDecks() != null) {
            for (Deck item : myDecks) {
                if (item.getName().equals(name)) {
                    view.deckAlreadyExists();
                    return false;
                }
            }
        }
        Deck deck = new Deck(name);
        myDecks.add(deck);
        view.deckCreated(deck);
        return true;
    }

    public boolean deleteDeck(String name) {
        Iterator<Deck> iter = myDecks.iterator();
        Deck deck;
        while (iter.hasNext()) {
            deck = iter.next();
            if (deck.getName().equals(name)) {
                iter.remove();
                return true;
            }
        }
        return false;
    }

    private boolean addCardToDeck(Card card, String deckName) {
        if (!myCards.contains(card)) {
            view.cardNotInCollection();
            return false;
        }
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck != null) {
            if (deck.getCards() == null) {
                deck.addCard(card);
            } else if (deck.numberOfCards() > 20) {
                view.tooManyCardsInDeck();
                return false;
            } else if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                for (Card item : deck.getCards()) {
                    if (item.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                        view.heroInDeck();
                        return false;
                    }
                }
                deck.addCard(card);
            } else if (!deck.getCards().contains(card)) {
                deck.addCard(card);
            } else if (deck.getCards().contains(card) && deck.getNumOfCardInDeck(card) >= card.numOfCardInCollection) {
                view.cardIsAlreadyInDeck();
                return false;
            } else {
                deck.addCard(card);
            }
        } else {
            view.deckIsNotInCollection();
            return false;
        }
        if (deck.getName().equals("")) {
            view.deckIsNotInCollection();
            return false;
        }
        return true;
    }

    private boolean addItemToDeck(Item item, String deckName) {
        if (!myItems.contains(item)) {
            view.itemNotInCollection();
            return false;
        }
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return false;
        }
        if (deck.getItem() != null) {
            view.tooManyItems();
            return false;
        }
        deck.addItem(item);
        return true;
    }

    private void removeCardFromDeck(String cardID, String deckName) {
        Card card = Card.returnCardById(cardID);
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
        } else if (card == null) {
            view.invalidCardId();
        } else if (!deck.getCards().contains(card)) {
            view.deckIsNotInCollection();
        } else {
            deck.deleteCard(card);
        }
    }

    private void removeItemFromDeck(String id, String deckName) {
        Item item = Item.getItemById(id);
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
        } else if (item == null) {
            view.invalidItemID();
        } else if (deck.getItem() != item) {
            view.itemNotInCollection();
        } else {
            deck.deleteItem(item);
        }
    }

    public void showMenu() {
        view.showCollectionMenu();
    }

    public boolean validateDeck(String deckName) {
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return false;
        } else {
            boolean ok;
            ok = deck.validateDeck();
            if (ok) {
                deck.validated = true;
                view.validatedDeck(deck.getName());
            } else {
                view.couldNotBeValidated(deck.getName());
            }
            return ok;
        }
    }

    public boolean selectDeck(String deckName) {
        Deck deck = Deck.returnDeckByName(deckName);
        if (deck == null) {
            view.deckIsNotInCollection();
            return false;
        }
        if (!deck.validateDeck()) {
            view.deckIsNotValid();
            return false;
        }
        try {
            Controller.currentAccount.setMainDeck(deck);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Controller.currentAccount.getMainDeck().validated = true;
        Controller.currentAccount.getMainDeck().getHand().initializeHand();
        view.setMainDeck();
        return true;
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
            view.noCardInThisDeck();
            if (deck.item == null) {
                view.noItemsInThisDeck();
                return;
            }
        }
        if (deck.cards != null) {
            view.showHeroInDeck(deck);
        }
        int i = 1;
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
        for (Item item1 : this.myItems()) {
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

    public boolean cardOrItemToDeck(String id, String deckName) {
        Card card = Card.returnCardById(id);
        Item item = Item.getItemById(id);
        if (card != null) {
            view.wantToAddCard(card);
            return addCardToDeck(card, deckName);
        } else if (item != null) {
            view.wantToAddItem(item);
            return addItemToDeck(item, deckName);
        }
        return false;
    }

    public boolean removeCardOrItemFromDeck(String group, String group1) {
        Card card = Card.returnCardById(group);
        Item item = Item.getItemById(group);
        if (card != null) {
            this.removeCardFromDeck(group, group1);
            return true;

        } else if (item != null) {
            this.removeItemFromDeck(group, group1);
            return true;
        }
        return false;
    }
}
