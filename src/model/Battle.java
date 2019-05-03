package model;

import controller.Controller;
import view.Request;
import view.View;

public class Battle {

    private View view = View.getInstance();

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

    public Battle() {

    }

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
