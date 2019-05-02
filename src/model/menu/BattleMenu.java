package model.menu;

import controller.Controller;
import model.Account;
import model.Game;
import view.InputException;
import view.Request;
import view.RequestType;

public class BattleMenu extends Menu {

    private static BattleMenu battleMenu = new BattleMenu();
    public static BattleMenu getInstance() {
        return battleMenu;
    }

    Request request = new Request();

    public void chooseBattleType(String command) throws InputException  {
        if (RequestType.SINGLE_PLAYER.setMatcher(command).find()) {
            System.out.println("single player mode!");
            //System.out.println("to start game , type 'start' ");
        }
        else if (RequestType.MULTI_PLAYER.setMatcher(command).find()) {
            System.out.println("multi player mode!");
            System.out.println("enter second player username: ");
            String secondUsername = request.getNewCommand();
            Controller.enemyAccount.setUsername(secondUsername);
            addSecondPLayer(secondUsername);
        }
        else {
            throw new InputException("invalid command");
        }
    }

    public void addSecondPLayer(String secondUsername) {
        Account account = Controller.currentAccount;
        Account secondPlayer = new Account();
        secondPlayer.setUsername(secondUsername);
        System.out.println("enter second player password: ");
        String password = request.getNewCommand();
        secondPlayer.setPassword(password);
        Controller.enemyAccount.setPassword(password);
        System.out.println("game is between " + account.getUsername() + " & " + secondPlayer.getUsername());
    }
}
