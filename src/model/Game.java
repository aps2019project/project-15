package model;

import controller.Controller;

import java.util.ArrayList;

enum ModeOfGame {
    StoryMode, killOpponent, keepFlag, CollectFlags
}

enum GameType {
    SinglePlayer, MultiPlayer
}

public class Game {

    private Map map = new Map();
    private int currentTurn;


    public Game() {
    }

    private GameType gameType;
    private ModeOfGame Mode;
    GameType type;
    ArrayList<Account> accounts;
    ArrayList<Card> cardsInGame;
    ArrayList<Card> graveYard;
    int turn = (int) (Math.random() % 2 + 1);
    private int result;
    private int timeOfGame;

    public Map getMap() {
        return map;
    }


    public Account setaccounts() {
        if (this.gameType.equals(GameType.MultiPlayer)) {
            Account player2 = new Account();
            return player2;
        }
        return null;
    }

    private int player1Mp = setInitialPlayer1Mp();
    private int player2Mp = setInitialPlayer2Mp();
    private int firstTurn;
    boolean done = false;


    public void addToHistory() {
        History history = new History();
        history.result = result;
        history.time = timeOfGame;
/*            player1.historyGames.add(history);
            player2.historyGames.add(history);*/

    }

    private int setInitialPlayer1Mp() {
        if (this.firstTurn == 1) {
            return 2;
        }
        return 3;
    }

    private int setInitialPlayer2Mp() {
        if (this.firstTurn == 2) {
            return 2;
        }
        return 3;
    }

    public void incrementNumOfMp() {

    }

    public void addMp() {

    }

    public void reduceMp() {

    }

    public boolean returnCondition() {
        return true;
    }

    public void endGame() {
        if (returnCondition()) {

        }
    }

    public void setTypeOfGame(int number) {
        switch (number) {
            case (1): {
                Mode = ModeOfGame.StoryMode;

                break;
            }
            case (2): {
                Mode = ModeOfGame.killOpponent;

                break;
            }
            case (3): {
                Mode = ModeOfGame.keepFlag;
                setFlag();
                break;
            }
            case (4): {
                Mode = ModeOfGame.CollectFlags;
                setFlag();
                break;
            }
        }
    }

    public boolean counterAttackPossible(Card myCard, Card opponentCard) {
        return true;
    }

    public void counterAttack(Card myCard, Card opponentCard) {

    }

    public void setFlag() {

    }

    public void showNextCard() {

    }

    public void enterGraveYard() {

    }

    public void setSecondPlayer() {
        if (this.gameType.equals(GameType.MultiPlayer)) {

        }
    }

    public int getTurn() {
        return turn;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}