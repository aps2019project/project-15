package model.menu;

import model.Account;
import model.Game;
import view.Request;
import view.RequestType;

public class BattleMenu extends Menu {

    private static BattleMenu battleMenu = new BattleMenu();
    public static BattleMenu getInstance() {
        return battleMenu;
    }

    Request request = new Request();

    public void chooseBattleType(String command) {
        Game game = new Game();
        if (RequestType.SINGLE_PLAYER.setMatcher(command).find()) {
            System.out.println("single player mode!");
        }
        else if (RequestType.MULTI_PLAYER.setMatcher(command).find()) {
            System.out.println("multi player mode!");
            String secondUsername = request.getNewCommand();

        }
    }

    public void addSecondPLayer(String secondUsername) {
        Account secondPlayer = new Account();
        secondPlayer.setUsername(secondUsername);
        secondPlayer.setPassword(request.getNewCommand());

    }
}
