package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Deck {

    ArrayList<Card> cards = new ArrayList<>();

    Hand hand = new Hand();

    Item item;
    String name = " ";
    View view = View.getInstance();
    public boolean validated = false;

    public Deck(String name) {
        this.name = name;
    }

    public Hand getHand() {
        return hand;
    }

    void addCard(Card card) {
        this.cards.add(card);
        view.cardAdded();
    }

    void deleteCard(Card card) {
        if (!this.cards.contains(card)) {
            view.cardIsNotInDeck();
            return;
        }
        cards.remove(card);
    }

    void addItem(Item item) {
        this.item = item;
        view.itemAdded();
    }

    void deleteItem(Item item) {
        if (!this.item.equals(item)) {
            view.itemIsNotInDeck();
            return;
        }
        this.item = null;
    }

    boolean validateDeck() {
        boolean oneHero = false;
        view.showNumOfCardsInDeck(this.numberOfCards());
        if (this.numberOfCards() == 21) {
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
                view.validDeck();
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    int numberOfCards() {
        return cards.size();
    }

    public Item getItem() {
        return item;
    }

    public static Deck returnDeckByName(String name) {
        for (Deck deck : Controller.currentAccount.getMyCollection().myDecks()) {
            if (deck.name.equals(name)) {
                return deck;
            }
        }
        return null;
    }

    int getNumOfCardInDeck(Card card) {
        int num = 0;
        for (Card card1 : this.getCards()) {
            if (card1 == card) {
                num++;
            }
        }
        return num;
    }

    public void showHand() {
        view.showHand(this.hand);
    }

    public Hero getDeckHero() {
        for (Card card : this.getCards()) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                return (Hero) card;
            }
        }
        return null;
    }

}
