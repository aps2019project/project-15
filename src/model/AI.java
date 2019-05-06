package model;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Account{
    private Game game;
    private Deck mainDdeck;
    private ArrayList<Card> AICards = new ArrayList<>();

    // private ArrayList<Deck> AIDecks;
    public AI( int i) {
        if(i == 1) {
            this.setMainDeck(Deck.AiDeck(1));
        }else if (i==2){
            this.setMainDeck(Deck.AiDeck(2));
            }
        else if (i == 3) {
        this.setMainDeck(Deck.AiDeck(3));
        }

    }
    public void setGame(Game game) {

        this.game = game;
    }

    private void insertcard() {
    }

    private void move() {

    }
   private void attack() {



    }
    public void PlayGame(){
        Random random = new Random();
        int action = random.nextInt() % 3;
        switch (action) {
            case 0:
                move();
                break;
            case 1:
                attack();
                break;
            case 2 :

                insertcard();
        }
                game.endTurn();
        }
    }

    public ArrayList<Card> getAICards() {
        return AICards;
    }

    public Deck getMainDdeck() {
        return mainDdeck;
    }
}
