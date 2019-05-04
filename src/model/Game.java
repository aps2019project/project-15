package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

public class Game {

    private Map map = new Map();
    private int currentTurn;
    private GameType gameType;
    private ModeOfGame Mode;
    GameType type;
    ArrayList<Account> accounts;
    ArrayList<Card> cardsInGame;
    ArrayList<Card> graveYard;
    private int turn = (int) (Math.random() % 2 + 1);
    private int result;
    private int timeOfGame;

    private View view = View.getInstance();

    public Map getMap() {
        return map;
    }

    public Account setAccounts() {
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


    public void addPlayerOneMp(int n) {
        this.player1Mp += n;
    }
    public void addPlayerTwoMp(int n) {
        this.player2Mp += n;
    }
    public void updateGraveYard() {
        //should be called after each game
        for (Card card : Controller.currentAccount.getCardsInGame()) {
            if (card.Hp <= 0) {
                graveYard.add(card);
                Controller.currentAccount.removeCardInGame(card);
            }
        }
        for (Card card : Controller.enemyAccount.getCardsInGame()) {
            if (card.Hp <= 0) {
                graveYard.add(card);
                Controller.enemyAccount.removeCardInGame(card);
            }
        }
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
        myCard.attack(opponentCard);

    }

    public void setFlag() {

    }

    public void showNextCard() {

    }

    public void setSecondPlayer() {
        if (this.gameType.equals(GameType.MultiPlayer)) {

        }
    }

    public int getTurn() {
        return turn;
    }

    public void switchTurn() {
        if (this.turn == 1) {
            this.turn = 2;
        } else if (this.turn == 2) {
            this.turn = 1;
        }
    }

/*
    public void chooseBattleType(String command) {
        Request request = new Request();

        System.out.println("1.Single player");
        String command = request.getNewCommand();
        System.out.println("2.Multi player");
        if (command.equals("single player")) {
            GameType gameType = GameType.SinglePlayer;
            System.out.println("Single player mode!");
            System.out.println("1.Story Mode!");
            System.out.println("2.Custom Game!");
            if (request.getNewCommand().equals("story mode")) {
                System.out.println("you chose Story Mode!");

            } else if (request.getNewCommand().toLowerCase().trim().equals("custom game")) {
                System.out.println("you chose Custom Game!");
            }
        } else if (command.equals("multi player")) {
            System.out.println("Multi player mode!!");
            GameType gameType = GameType.MultiPlayer;
            Account account2 = new Account();
            if (account2.checkUserAndPass()) {
                System.out.println("game is between " + Controller.currentAccount + " and " + account2.getUsername());
                System.out.println("battle started!");
            }
            view.playerOptions();
            int number = view.enteredNum();
            if (number == 1) {
                view.singlePlayerChosen();
            } else if (number == 2) {
                view.multiPlayerChosen();
                Account account3 = new Account();
                account2.setUserAndPass();
                view.gameDeclaration(account2);
            }
        }
    }
*/

    public void showMyMinions() {

    }

    public void showOpponentMinions() {

    }

    public void showCardInfo(String cardId) {

    }

    public void select(String cardId) {

    }

    public void moveTo(int x, int y) {

    }


    public void attack(Card myCard, Card opponentCard) {

    }

    public boolean attackComboPossible(String... opponentCardId) {
        return true;

    }

    public void attackCombo(String opponentCardId, String myCardId1, String myCardId2, String... myCard) {

    }

    public void useSpecialPower(int x, int y) {

    }

    public void showHand() {

    }

    public void insert(String CardId, int x, int y) {

    }

    public void endTurn() {

    }

    public void help() {
        view.showMinionsYouCanMove();
        view.showMinionsYouCanAttack();
        view.showMinionsYouCanAttack();
    }
}