package model.menu;

import controller.Controller;
import model.Account;
import model.DataCenter;
import model.Game;
import model.ModeOfGame;
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

    public void chooseBattleType(Game game , String command) throws InputException {
        if (RequestType.SINGLE_PLAYER.setMatcher(command).find()) {
            view.singlePlayerMode();
            chooseBattleMode();
        } else if (RequestType.MULTI_PLAYER.setMatcher(command).find()) {
            boolean secondPlayerChosen = false;
            while (!secondPlayerChosen) {
                view.getSecondUsername();
                String secondUsername = request.getNewCommand();
                if(RequestType.EXIT.setMatcher(secondUsername).find()){
                    Controller.getInstance().setExit(false);
                    return;
                }
                Controller.enemyAccount = DataCenter.getInstance().getAccountByName(secondUsername);
                if (Controller.enemyAccount == null) {
                    Controller.enemyAccount.setUsername(secondUsername);
                    addSecondPLayer();
                } else {
                    loginToSecondPlayer();
                }
                secondPlayerChosen = true;
                if (Controller.enemyAccount.getMainDeck().getDeckHero() == null) {
                    view.enemyWithoutMainDeck();
                    secondPlayerChosen = false;
                }
            }
            String setMode = request.getNewCommand();
            while (!RequestType.START_MULTIPLAYER_GAME.setMatcher(setMode).find()){
                System.out.println("Invalid Mode!");
                setMode = request.getNewCommand();
            }
            if (RequestType.START_MULTIPLAYER_GAME.setMatcher(setMode).find()) {
                int mode = Integer.parseInt(RequestType.START_MULTIPLAYER_GAME.getMatcher().group(1));
                int numOfFlags = Integer.parseInt(RequestType.START_MULTIPLAYER_GAME.getMatcher().group(2));
                if (mode == 1) {
                    Controller.currentGame.setMode(ModeOfGame.killOpponent);
                    System.out.println("kill Opponent");
                } else if (mode == 2) {
                    Controller.currentGame.setMode(ModeOfGame.KeepFlag);
                    System.out.println("Keep Flags");
                    game.addFlagsToGame(numOfFlags);
                } else if (mode == 3) {
                    Controller.currentGame.setMode(ModeOfGame.CollectFlags);
                    System.out.println("Collect Flags");
                } else{
                    view.invalidNumber();
                }
                if (numOfFlags != 0) {
                    view.showNumOfFlags(numOfFlags);
                }
                System.out.println("game mode is: " + Controller.currentGame.getMode());
            }
        } else {
            throw new InputException("Invalid command");
        }
    }

    private void loginToSecondPlayer() {
        view.getSecondPassword();
        String password = request.getNewCommand();
        while (!password.equals(Controller.enemyAccount.getPassword())){
            view.rightPassword();
            password = request.getNewCommand();
        }
        view.gameIsBetween(Controller.currentAccount, Controller.enemyAccount);
    }

    private void addSecondPLayer() {
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
