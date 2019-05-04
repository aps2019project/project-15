package model.menu;

import controller.Controller;
import model.Account;
import model.Game;
import view.InputException;
import view.Request;
import view.RequestType;
import view.View;

public class BattleMenu extends Menu {

    private static BattleMenu battleMenu = new BattleMenu();
    public static BattleMenu getInstance() {
        return battleMenu;
    }
    private View view = View.getInstance();

    Request request = new Request();

    public void chooseBattleType(String command) throws InputException  {
        if (RequestType.SINGLE_PLAYER.setMatcher(command).find()) {
            view.singlePlayerMode();
        }
        else if (RequestType.MULTI_PLAYER.setMatcher(command).find()) {
            view.getSecondUsername();
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
            view.getSecondPassword();
            String password = request.getNewCommand();
            secondPlayer.setPassword(password);
            Controller.enemyAccount.setPassword(password);
            view.gameIsBetween(account, secondPlayer);
            view.gameIsBetween(account, Controller.enemyAccount);
    }
}
