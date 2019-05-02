package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Deck {

    ArrayList<Card> cards;
    ArrayList<Card> hand;
    Item item = new Collectible();
    String name;
    View view = View.getInstance();
    public Deck(String name){
        this.name = name;
    }

    public void addCard(Card card){
        this.cards.add(card);
        view.cardAdded();
    }
    public void deleteCard(Card card){
        if(!this.cards.contains(card)){
            view.cardIsNotInDeck();
            return;
        }
        cards.remove(card);
    }
    public void addItem(Item item){
        this.item = item;
        view.itemAdded();
    }
    public void deleteItem(Item item){
        if(!this.item.equals(item)){
            view.itemIsNotInDeck();
            return;
        }
        this.item = null;
    }
    public boolean validateDeck(){
        boolean oneHero = false;
        if(this.numberOfCards() == 20){
            for(Card item : cards){
                if(item.getTypeOfAttack().equals(TypeOfCard.Hero)){
                    if(oneHero){
                        oneHero = false;
                    }
                    else{
                        oneHero = true;
                    }
                }
            }
            if(oneHero){
                return true;
            }
        }
        return false;
    }
    public Hand returnHand(){
return null;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public int numberOfCards(){
        return cards.size();
    }

    public Item getItem() {
        return item;
    }
    public static Deck returnDeckByName(String name){
        for(Deck deck : Controller.currentAccount.myDecks){
            if(deck.name.equals(name)){
                return deck;
            }
        }
        return null;
    }
}
