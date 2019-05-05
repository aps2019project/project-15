package model;

import java.util.ArrayList;
import java.util.Random;

public class Hand {
    ArrayList<Card> allCradsInDeck = new ArrayList<>();
    ArrayList<Card> cardsInHand = new ArrayList<>();
    ArrayList<Card> cardsUsedInGame = new ArrayList<>();
    Card cardToBeAdded;

    public void initializeHand(){
        //should be called at the begining of game
        Hero hero = new Hero();
        for(Card card : allCradsInDeck){
            if(card.getTypeOfAttack().equals(TypeOfCard.Hero)){
                hero = (Hero) card;
                break;
            }
        }
        allCradsInDeck.remove(hero);
        Random random = new Random();
        cardsInHand.add(allCradsInDeck.get(random.nextInt(allCradsInDeck.size())));
        int j = 4;
        for(int i = 0; i < j ; i++){
            Card card = allCradsInDeck.get(random.nextInt(allCradsInDeck.size()));
            if(!cardsInHand.contains(card)){
                cardsInHand.add(card);
            }
            else {
                j++;
            }
        }
    }
    public void getNextCard() {
        Random random = new Random();
        int j = 1;
        for(int i = 0; i < j; i++){
            Card card = allCradsInDeck.get(random.nextInt(allCradsInDeck.size()));
            if(!cardsInHand.contains(card) && !cardsUsedInGame.contains(card)){
                cardToBeAdded = card;
            }
            else if((allCradsInDeck.size() - cardsUsedInGame.size() - cardsInHand.size()) == 0){
                cardToBeAdded = null;
                break;
            }
            else{
                j++;
            }
        }
    }

    public ArrayList<Card> returnHand() {
        if(cardsInHand.size() < 5){
            addToHand();
        }
        return cardsInHand;
    }
    public void addToHand() {
        while(cardsInHand.size() < 5){
            cardsInHand.add(cardToBeAdded);
            if((allCradsInDeck.size() - cardsUsedInGame.size() - cardsInHand.size()) == 0){
                break;
            }
            getNextCard();
        }
    }
    public void deleteFromHand(Card card){
        cardsInHand.remove(card);
        cardsUsedInGame.add(card);
    }
}
