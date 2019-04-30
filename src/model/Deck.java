package model;

import view.View;

import java.util.ArrayList;

class Deck {

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
}
