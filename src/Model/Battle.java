package Model;

import Controller.Controller;

public class Battle {

    public void chooseBattleType() {
        System.out.println("1.Single player");
        System.out.println("2.Multi player");
        int number = Integer.parseInt(Controller.scanner.nextLine());
        if (number == 1) {
            System.out.println("Singke player mode!");
        } else if (number == 2) {
            System.out.println("Multi player mode!!");
            Account account2 = new Account();
            account2.setUserAndPass();
            System.out.println("game is between " + Controller.account.getUsername() + " and " + account2.getUsername() );
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
