package model;

import java.util.ArrayList;
import java.util.Random;

public class AI extends Account {
    private static AI ai = new AI();
    private Game game;
    private Deck mainDeck;
    private ArrayList<Card> AICards = new ArrayList<>();

    public void setGame(Game game) {
        this.game = game;
    }

    public static AI getInstance() {
        return ai;
    }

    private AI() {

    }

    private void insertCard() {
    }

    private void move() {

    }

    private void attack() {

    }

    public void PlayGame() {
        Random random = new Random();
        int action = random.nextInt() % 3;
        switch (action) {
            case 0:
                move();
                break;
            case 1:
                attack();
                break;
            case 2:

                insertCard();
        }
        game.endTurn();
    }


    public ArrayList<Card> getAICards() {
        return AICards;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }
}
