package model;

import java.util.ArrayList;

public class Hand {
    ArrayList<Card> allCardsInDeck = new ArrayList<>();
    private ArrayList<Card> cardsInHand = new ArrayList<>();
    private ArrayList<Card> cardsUsedInGame = new ArrayList<>();
    private Card cardToBeAdded;
    private int index = 0;

    public void initializeHand() {
        Hero hero = new Hero();
        for (Card card : allCardsInDeck) {
            if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                hero = (Hero) card;
                break;
            }
        }
        allCardsInDeck.remove(hero);
        for(Card card : allCardsInDeck) {
            if(index < 6) {
                cardsInHand.add(card);
                index++;
            }
            else {
                break;
            }
        }
    }

    public Card getCardToBeAdded() {
        if (cardToBeAdded == null) {
            getNextCard();
        }
        return cardToBeAdded;
    }

    private void getNextCard() {
        if(index < allCardsInDeck.size()){
            cardToBeAdded = allCardsInDeck.get(index);
            index++;
        }
        else {
            cardToBeAdded = null;
        }
    }

    public ArrayList<Card> returnHand() {
        while (cardsInHand.size() < 6) {
            if(!addToHand()){
                break;
            }
        }
        return cardsInHand;
    }

    private boolean addToHand() {
        while (cardsInHand.size() < 6) {
            if(cardToBeAdded != null) {
                cardsInHand.add(cardToBeAdded);
            }
            else {
                return false;
            }
            getNextCard();
        }
        return true;
    }

    public void deleteFromHand(Card card) {
        cardsInHand.remove(card);
        cardsUsedInGame.add(card);
    }

}
