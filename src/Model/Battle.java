package Model;

import Controller.Controller;

public class Battle {

    public void chooseBattleType() {
        Controller.view.playerOptions();
        int number = Controller.view.enteredNum();
        if (number == 1) {
            Controller.view.singlePlayerChosen();
        } else if (number == 2) {
            Controller.view.multiPlayerChosen();
            Account account2 = new Account();
            account2.setUserAndPass();
            Controller.view.gameDeclaration(account2);
        }

    }

    private static Battle ourInstance = new Battle();

    public static Battle getInstance() {
        return ourInstance;
    }

    Map map;

    Battle() {
        map = Controller.getMap();
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

    public void showhand() {

    }

    public void insert(String CardId, int x, int y) {

    }

    public void endTurn() {

    }

    public void help() {

        Controller.view.showMinionsYouCanMove();
        Controller.view.showMinionsYouCanAttack();
        Controller.view.showMinionsYouCanAttack();
    }
}
