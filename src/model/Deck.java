package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Deck {

    ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Card> hand = new ArrayList<>();
    Item item;
    String name;
    View view = View.getInstance();

    public Deck(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        this.cards.add(card);
        view.cardAdded();
    }

    public void deleteCard(Card card) {
        if (!this.cards.contains(card)) {
            view.cardIsNotInDeck();
            return;
        }
        cards.remove(card);
    }

    public void addItem(Item item) {
        this.item = item;
        view.itemAdded();
    }

    public void deleteItem(Item item) {
        if (!this.item.equals(item)) {
            view.itemIsNotInDeck();
            return;
        }
        this.item = null;
    }

    public boolean validateDeck() {
        boolean oneHero = false;
        System.out.println("number of cards in this deck: "+this.numberOfCards());
        if (this.numberOfCards() == 21 ) {
            for (Card item : cards) {
                if (item.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                    if (oneHero) {
                        return false;
                    } else {
                        oneHero = true;
                    }
                }
            }
            if (oneHero) {
                System.out.println("deck is valid!");
                return true;
            }
        }
        return false;
    }

    public Hand returnHand() {
        return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int numberOfCards() {
        return cards.size();
    }

    public Item getItem() {
        return item;
    }

    public static Deck returnDeckByName(String name) {
        for (Deck deck : Controller.currentAccount.getMyCollection().getMyDecks()) {
            if (deck.name.equals(name)) {
                return deck;
            }
        }
        return null;
    }

    public int getNumOfCardInDeck(Card card) {
        int num = 0;
        for (Card card1 : this.getCards()) {
            if (card1 == card) {
                num++;
            }
        }
        return num;
    }
}
