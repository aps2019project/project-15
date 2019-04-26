package controller;

import model.Account;
import model.Battle;
import model.Map;
import model.menu.*;
import view.InputException;
import view.Request;
import view.RequestType;
import view.View;

import javax.xml.stream.events.EntityReference;
import java.util.*;

public class Controller {

    private Request request = new Request();
    private View view = View.getInstance();
    public static Menu currentMenu = new AccountMenu();

    private static void setCurrentMenu() {
        Controller.currentMenu = AccountMenu.getInstance();
    }

    private static DataCenter dataCenter = DataCenter.getInstance();

    public static Account currentAccount = new Account();

    public void main() {
        Request request = new Request();
        setCurrentMenu();
        while (true) {
            try {
                handleRequest(currentMenu, request.getNewCommand());
            } catch (InputException e) {
                View.getInstance().printError(e);
            }
        }
    }

/*    public void handler(String command) {
        dataCenter;
    }*/

    private void handleRequest(Menu currentMenu, String command) throws InputException {
        DataCenter dataCenter = DataCenter.getInstance();
        if (currentMenu.equals(AccountMenu.getInstance())) {
            accountMenuRequest(command);
        } else if (currentMenu.equals(BattleMenu.getInstance())) {
            battleMenuRequest(command, dataCenter);
        } else if (currentMenu.equals(CollectionMenu.getInstance())) {
            collectionMenuRequest(command, dataCenter);
        } else if (currentMenu.equals(ShopMenu.getInstance())) {
            shopMenuRequest(command, dataCenter);
        }
    }

    private void accountMenuRequest(String command) {
        AccountMenu accountMenu = AccountMenu.getInstance();
        if (Controller.currentMenu.equals(accountMenu)) {
            if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                System.out.print("your password: ");
                request.getNewCommand();
                Controller.currentAccount = accountMenu.register(username, command);
            } else if (RequestType.LOGIN.setMatcher(command).find()) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                System.out.println("enter your password: ");
                request.getNewCommand();
                accountMenu.loginFunction(username, command, dataCenter);
            } else if (RequestType.SHOW_LEADER_BOARD.setMatcher(command).find()) {
                leaderBoard(dataCenter);
            } else if (RequestType.SAVE.setMatcher(command).find()) {
                System.out.println("you saved everything!");
            } else if (RequestType.LOGOUT.setMatcher(command).find()) {
                Account account = Controller.currentAccount;
                account.setLoggedIn(false);
                System.out.println("you logged out!");
            } else if (RequestType.HELP.setMatcher(command).find()) {
                view.printMainMenuOfGame();
            } else if (RequestType.ENTER_COLLECTION.setMatcher(command).find()) {
                view.enterCollection();
            } else if (RequestType.ENTER_SHOP.setMatcher(command).find()) {
                view.enterShop();
            } else if (RequestType.ENTER_BATTLE.setMatcher(command).find()) {
                Battle battle = new Battle();
                view.enterBattle();
                request.getNewCommand();
            } else if(RequestType.EXIT.setMatcher(command).find()) {
                view.exitMessage();
                currentAccount.setLoggedIn(false);
                view.logOutMessage();
            }
        }
    }


    private void battleMenuRequest(String command, DataCenter dataCenter) {
        BattleMenu battleMenu = new BattleMenu();
        if (currentMenu.equals(battleMenu)) {
            System.out.println("welcome to battle menu!");
        }
    }

    private void collectionMenuRequest(String command, DataCenter dataCenter) {
        CollectionMenu collectionMenu = CollectionMenu.getInstance();
        if (currentMenu.equals(collectionMenu)) {
            System.out.println("welcome to collection");
        }
    }

    private void shopMenuRequest(String command, DataCenter dataCenter) {
        ShopMenu shopMenu = new ShopMenu();
        if (currentMenu.equals(shopMenu)) {
            System.out.println("welcome to shop menu!");
        }
    }

    private void leaderBoard(DataCenter dataCenter) {
        int num = 1;
        for (Account account : dataCenter.getAccounts().values()) {
            view.showLeaderBoard(account, num);
            num++;
        }
    }
}
