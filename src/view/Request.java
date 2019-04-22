package view;

import controller.Controller;
import controller.DataCenter;
import model.Account;
import model.Battle;
import model.menu.*;

import java.util.Collection;
import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    private String command;
    private View view = View.getInstance();

    public String getNewCommand() {
        return command = scanner.nextLine().toLowerCase().trim();
    }

    public int enteredNum(){
        return Integer.parseInt(scanner.nextLine().trim());
    }
    public void handleRequest(Menu currentMenu, DataCenter dataCenter) throws InputException {
        if (currentMenu.equals(AccountMenu.getInstance())) {
            accountMenuRequest(dataCenter);
        } else if (currentMenu.equals(BattleMenu.getInstance())) {
            battleMenuRequest(dataCenter);
        } else if (currentMenu.equals(CollectionMenu.getInstance())) {
            collectionMenuRequest(dataCenter);
        } else if (currentMenu.equals(ShopMenu.getInstance())) {
            shopMenuRequest(dataCenter);
        }
    }


    public void accountMenuRequest(DataCenter dataCenter) {
        AccountMenu accountMenu = AccountMenu.getInstance();
        while (Controller.currentMenu.equals(accountMenu)) {
            getNewCommand();
            if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                System.out.print("your password: ");
                getNewCommand();
                Controller.currentAccount = accountMenu.register(username, command, dataCenter);
            } else if (RequestType.LOGIN.setMatcher(command).find()) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                System.out.println("enter your password: ");
                getNewCommand();
                String password = command;
                accountMenu.loginFunction(username, password, dataCenter);
            } else if (RequestType.SHOW_LEADER_BOARD.setMatcher(command).find()) {
                view.showLeaderBoard(dataCenter);
            } else if (RequestType.SAVE.setMatcher(command).find()) {

            } else if (RequestType.LOGOUT.setMatcher(command).find()) {
                Account account = Controller.currentAccount;
                account.setLoggedIn(false);
                System.out.println("you logged out!");
            } else if (RequestType.HELP.setMatcher(command).find()) {
                view.printMainMenuOfGame();
                getNewCommand();
                choosePartsOfMenu(Controller.currentAccount, command);
            }
        }
    }


    public void battleMenuRequest(DataCenter dataCenter) {

    }

    public void collectionMenuRequest(DataCenter dataCenter) {

    }

    public void shopMenuRequest(DataCenter dataCenter) {

    }

    public void chooseBattleType() {
        DataCenter dataCenter = DataCenter.getInstance();
        System.out.println("Single player mode!");
        System.out.println("Story Mode?");
        System.out.println("Custom Game?");
        getNewCommand();
        if (command.equals("story mode")) {
            System.out.println("you chose Story Mode!");
        } else if (command.equals("custom game")) {
            System.out.println("you chose Custom Game!");
        }
    }


    public void choosePartsOfMenu(Account account, String menuPart) {
        switch (menuPart) {
            case ("enter collection"): {
                view.enterCollection();
                break;
            }
            case ("enter shop"): {
                view.enterShop();
                break;
            }
            case ("enter battle"): {
                Battle battle = new Battle();
                view.enterBattle();
                getNewCommand();
                //battle.chooseBattleType(command);
                break;
            }
            case ("exit"): {
                view.exitMessage();
                account.setLoggedIn(false);
                view.logOutMessage();
                break;
            }
            case ("help"): {
                System.out.println("you entered help!");
                view.printMainMenuOfGame();
            }

        }
    }
}
