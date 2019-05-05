package model;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Account{
Game game = new Game();
    private Deck mainDdeck;
    private ArrayList<Card> AICards = new ArrayList<>();
   // private ArrayList<Deck> AIDecks;
    public AI (){}
    private void insertcard(){}
    private void move() {

    }
   private void attack() {



    }
    public void PlayGame(){
        Random random = new Random();
        int action =random.nextInt()%3;
        switch (action){
            case 0:
                move();
                break;
            case 1:
                attack();
                break;
            case 2 :

                insertcard();
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
