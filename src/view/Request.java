package view;

import controller.Controller;
import controller.DataCenter;
import model.Account;
import model.Battle;
import model.menu.AccountMenu;
import model.menu.BattleMenu;
import model.menu.Menu;

import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    private String command;
    private View view = View.getInstance();

    public String getNewCommand() {
        return command = scanner.nextLine().toLowerCase().trim();
    }

    public void handleRequest(Menu currentMenu, DataCenter dataCenter) throws InputException {
        if (currentMenu.equals(AccountMenu.getInstance())) {
            accountMenuRequest(dataCenter);
        } else if (currentMenu.equals(BattleMenu.getInstance())) {
            battleMenuRequest(dataCenter);
        }
    }


    public void accountMenuRequest(DataCenter dataCenter) {
        AccountMenu accountMenu = AccountMenu.getInstance();
        if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
            String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
            System.out.print("your password: ");
            getNewCommand();
            Controller.currentAccount = accountMenu.register(username, command, dataCenter);
            getNewCommand();
            if (command.equals("help")) {
                view.printMainMenuOfGame();
            }
            getNewCommand();
            choosePartsOfMenu(Controller.currentAccount , command);
        }
    }

    public void battleMenuRequest(DataCenter dataCenter){

    }

    public void chooseBattleType() {
        DataCenter dataCenter = DataCenter.getInstance();

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
                battle.chooseBattleType(command);
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
