package model;

import controller.Controller;

public class Battle {

    public void chooseBattleType() {
        Game game = new Game();
        System.out.println("1.Single player");
        System.out.println("2.Multi player");
        String command = Controller.getCommand();
        if (command.equals("single player")) {
            GameType gameType = GameType.SinglePlayer;
            System.out.println("Single player mode!");
            System.out.println("1.Story Mode!");
            System.out.println("2.Custom Game!");
            if (Controller.getCommand().equals("story mode")) {
                System.out.println("you chose Story Mode!");

            }
            else if(Controller.scanner.nextLine().toLowerCase().trim().equals("custom game")){
                System.out.println("you chose Custom Game!");
            }
        } else if (command.equals("multi player")) {
            System.out.println("Multi player mode!!");
            GameType gameType = GameType.MultiPlayer;
            Account account2 = new Account();
            if (account2.checkUserAndPass()) {
                    System.out.println("game is between " + Controller.view.account.getUsername() + " and " + account2.getUsername());
                System.out.println("battle started!");
            }
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
