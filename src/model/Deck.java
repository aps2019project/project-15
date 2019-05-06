package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Deck {

    ArrayList<Card> cards = new ArrayList<>();
    Hand hand;
    Item item;
    String name;
    View view = View.getInstance();
    public boolean validated = false;

    Deck(String name) {
        this.name = name;
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
        System.out.println("number of cards in this deck: " + this.numberOfCards());
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

    ArrayList<Card> getCards() {
        return cards;
    }

    int numberOfCards() {
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
    public static Deck AiDeck(int number){

        int []AiItem = {51 , 68 , 62};
        int [] AiHero = {101 , 105 , 107};
        int [][] AiMinion = {{1 , 9 , 11 , 11 , 13 , 17 , 18 , 21 , 22 , 26 , 38 , 36 , 40 },{2,3,5,8,12,15,15,19,23,27,30,33,39},{6,7,10,14,16,16,20,24,25,28,29,31,34}};
        int [][]
    }
}
