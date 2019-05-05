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
    ArrayList<Card> cardsInGame;
    ArrayList<Card> graveYard;
    private int turn = (int) (Math.random() % 2 + 1);
    private int result;
    private int timeOfGame;
    private Card currentCrad;
    private Account activeAccount;

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

    public void setMode(ModeOfGame mode) {
        Mode = mode;
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

    public void reducePlayerOneMp(int n) {
        player1Mp -= n;
    }

    public void reducePlayerTwoMp(int n) {
        player2Mp -= n;
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
                Mode = ModeOfGame.KeepFlag;
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
        Card card = Card.returnCardById(cardId);
        if (card == null) {
            View.getInstance().invalidCardId();
            return;
        }
        currentCrad = card;
    }

    public void moveTo(Card card, int x, int y) {
        if (distanceTooLong(card, x, y)) {
            view.invalidTarget();
            return;
        }
        if (x < 0 || x > 5 || y < 0 || y > 9) {
            view.invalidTarget();
            return;
        }
        Block block = map.getBlock(x, y);
        if (block.isEmpty()) {
            view.invalidTarget();
            return;
        }
        if (!enemyInWay(block, card)) {
            view.invalidTarget();
            return;
        }
        card.getCurrentBlock().card = null;
        card.getCurrentBlock().setEmpty(true);
        block.setEmpty(false);
        block.card = card;
        card.setCurrentBlock(block);
        view.cardMoved(card);
    }

    private boolean enemyInWay(Block block, Card card) {
        if (block.x == card.getCurrentBlock().x && block.y > card.getCurrentBlock().y) {
            Block checkBlock = map.getBlock(block.x, block.y + 1);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.y == card.getCurrentBlock().y && block.x > card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x + 1, block.y);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.x == card.getCurrentBlock().x && block.y < card.getCurrentBlock().y) {
            Block checkBlock = map.getBlock(block.x, block.y - 1);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        if (block.y == card.getCurrentBlock().y && block.x < card.getCurrentBlock().x) {
            Block checkBlock = map.getBlock(block.x - 1, block.y);
            if (checkBlock.isEmpty()) {
                return false;
            }
            return true;
        }
        return true;
    }

    private boolean distanceTooLong(Card card, int x, int y) {
        return (Math.abs(card.getCurrentBlock().x - x) + Math.abs(card.getCurrentBlock().y - y)) <= 2;
    }


    public void attack(Card myCard, Card opponentCard) {
        myCard.attack(opponentCard);
    }
    public void attackCombo(String opponentCardId, String... myCardIds) {
        Card enemyCard = Card.returnCardById(opponentCardId);
        Account otherAccount;
        if(Controller.currentAccount.getCardsInGame().contains(enemyCard)){
            otherAccount = Controller.enemyAccount;
        }
        else if(Controller.enemyAccount.getCardsInGame().contains(enemyCard)){
            otherAccount = Controller.currentAccount;
        }
        else {
            view.cardNotInGame();
            return;
        }
        int lenght = myCardIds.length;
        String[] myCardId = new String[lenght];
        Minion[] myCrads = new Minion[lenght];
        for(int i = 0; i < lenght; i++){
            Card card = Card.returnCardById(myCardId[i]);
            if(!otherAccount.getCardsInGame().contains(card)){
                view.cardNotInGame();
                return;
            }
            if(!card.getTypeOfAttack().equals(TypeOfCard.Minion)){
                view.wrongCardTypeForCombo();
                return;
            }
            Minion minion = (Minion) card;
            if(!minion.getActivationTime().equals(SpecialPowerActivation.combo)){
                view.notAComboMinion();
                return;
            }
            myCrads[i] = minion;
        }
        Minion.comboAttack(enemyCard, myCrads);
    }

    public void useSpecialPower(int x, int y) {

    }

    public void showHand() {

    }

    public void insert(String CardId, int x, int y) {

    }

    public void endTurn() {
        turn++;
        if (activeAccount.equals(Controller.currentAccount)) {
            activeAccount = Controller.enemyAccount;
        } else {
            activeAccount = Controller.currentAccount;
        }
        for (Card card : activeAccount.getCardsInGame()) {
            card.attackedThisTurn = false;
        }
        updateGraveYard();
    }

    public void help() {
        view.showMinionsYouCanMove();
        view.showMinionsYouCanAttack();
        view.showMinionsYouCanAttack();
    }
}