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
    private Request request = new Request();

    public void chooseBattleType(String command) throws InputException {
        if (RequestType.SINGLE_PLAYER.setMatcher(command).find()) {
            view.singlePlayerMode();
        } else if (RequestType.MULTI_PLAYER.setMatcher(command).find()) {
            view.getSecondUsername();
            String secondUsername = request.getNewCommand();
            Controller.enemyAccount.setUsername(secondUsername);
            addSecondPLayer();
        } else {
            throw new InputException("Invalid command");
        }
    }

    public void addSecondPLayer() {
        Account account = Controller.currentAccount;
        view.getSecondPassword();
        String password = request.getNewCommand();
        Controller.enemyAccount.setPassword(password);
        view.gameIsBetween(account, Controller.enemyAccount);
    }

    public void chooseBattleMode() {
        view.modeGame();
        String command = request.getNewCommand();
        if (RequestType.STORY_MODE.setMatcher(command).find()) {
            view.choseStoryMode();
            Controller.currentGame.setTypeOfGame(0);
        } else if (RequestType.CUSTOM_GAME.setMatcher(command).find()) {
            view.choseCustomGame();
            view.setYourGameGoal();
            setGameGoal();
        }
    }

    private void setGameGoal() {
        String command = request.getNewCommand();
        int num = 0;
        if (RequestType.KILL_OPPONENT.setMatcher(command).find()) {
            Controller.currentGame.setTypeOfGame(1);
            num = 1;
        } else if (RequestType.GET_FLAG.setMatcher(command).find()) {
            Controller.currentGame.setTypeOfGame(2);
            num = 2;
        } else if (RequestType.COLLECT_FlAG.setMatcher(command).find()) {
            Controller.currentGame.setTypeOfGame(3);
            num = 3;
        }
        view.showGameGoal(num);
    }
}
