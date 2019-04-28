package controller;

import com.google.gson.Gson;
import model.*;
import model.menu.*;
import view.InputException;
import view.Request;
import view.RequestType;
import view.View;

import java.io.*;
import java.util.ArrayList;

public class Controller {

    private Request request = new Request();
    private View view = View.getInstance();
    public static Menu currentMenu = new AccountMenu();
    private boolean finishGame = false;
    private static DataCenter dataCenter = DataCenter.getInstance();

    private static void setCurrentMenu() {
        currentMenu = AccountMenu.getInstance();
    }

    public static Account currentAccount = new Account();

    public void main() {
        Request request = new Request();
        //initEverything();
        setCurrentMenu();
        while (!finishGame) {
            try {
                handleRequest(currentMenu, request.getNewCommand());
            } catch (InputException e) {
                View.getInstance().printError(e);
            }
        }
    }

/*
    private void initEverything() {
        try {
            final String[] paths = {
                    "../HeroNames"
            };
            for (String path : paths) {
                File directory = new File(path);
                File[] files = directory.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (path.contains("Hero")) {
                            addCard(file, Hero.class, dataCenter.getHeroes());
                        } else if (path.contains("Item")) {
                            addCard(file, Item.class, dataCenter.getItems());
                        } else if (path.contains("Minion")) {
                            addCard(file, Minion.class, dataCenter.getMinions());
                        } else if (path.contains("Spell")) {
                            addCard(file, Spell.class, dataCenter.getSpells());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> void addCard(File file, Class<T> classOfT, ArrayList<T> list) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        T card = new Gson().fromJson(reader, classOfT);
        list.add(card);
    }
*/

    private void handleRequest(Menu currentMenu, String command) throws InputException {
        DataCenter dataCenter = DataCenter.getInstance();
        if (currentMenu.equals(MainMenu.getInstance())) {
            mainMenuRequest(command);
        } else if (currentMenu.equals(AccountMenu.getInstance())) {
            accountMenuRequest(command);
        } else if (currentMenu.equals(BattleMenu.getInstance())) {
            battleMenuRequest(command);
        } else if (currentMenu.equals(CollectionMenu.getInstance())) {
            collectionMenuRequest(command, dataCenter);
        } else if (currentMenu.equals(ShopMenu.getInstance())) {
            shopMenuRequest(command);
        } else {
            throw new InputException("invalid command");
        }
    }

    private void mainMenuRequest(String command) throws InputException {
        Menu mainMenu = MainMenu.getInstance();
        if (currentMenu.equals(mainMenu)) {
            if (RequestType.SAVE.setMatcher(command).find()) {
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
                currentMenu = BattleMenu.getInstance();
            } else if (RequestType.EXIT.setMatcher(command).find()) {
                view.exitMessage();
                currentAccount.setLoggedIn(false);
                view.logOutMessage();
                currentMenu = AccountMenu.getInstance();
            } else {
                throw new InputException("invalid command");
            }
        }
    }

    private void accountMenuRequest(String command) throws InputException {
        AccountMenu accountMenu = AccountMenu.getInstance();
        if (Controller.currentMenu.equals(accountMenu)) {
            if (RequestType.CREATE_ACCOUNT.setMatcher(command).find() && !RequestType.CREATE_ACCOUNT.getMatcher().group(1).equals("")) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                while (true) {
                    if (!dataCenter.getAccounts().keySet().contains(username)) {
                        System.out.print("enter your password: ");
                        command = request.getNewCommand();
                        if (command.length() >= 4) {
                            Controller.currentAccount = accountMenu.register(username, command);
                            currentMenu = MainMenu.getInstance();
                            break;
                        } else {
                            System.out.println("password is too short! try again.");
                        }
                    } else {
                        System.out.println("username is not valid!");
                        break;
                    }
                }
            } else if (RequestType.LOGIN.setMatcher(command).find()) {
                String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
                System.out.println("enter your password: ");
                command = request.getNewCommand();
                accountMenu.loginFunction(username, command, dataCenter);
                currentMenu = MainMenu.getInstance();
            } else if (RequestType.SHOW_LEADER_BOARD.setMatcher(command).find()) {
                leaderBoard(dataCenter);
            } else if (RequestType.HELP.setMatcher(command).find()) {
                view.printAccountMenuOfGame();
            } else if (RequestType.EXIT.setMatcher(command).find()) {
                view.exitMessage();
                finishGame = true;
            }
        } else {
            throw new InputException("invalid command");
        }
    }

    private void battleMenuRequest(String command) throws InputException {
        BattleMenu battleMenu = BattleMenu.getInstance();
        if (currentMenu.equals(battleMenu)) {
            battleMenu.chooseBattleType(command);
        } else {
            throw new InputException("invalid command");
        }
    }

    private void collectionMenuRequest(String command, DataCenter dataCenter) throws InputException {
        CollectionMenu collectionMenu = CollectionMenu.getInstance();
        if (currentMenu.equals(collectionMenu)) {
            System.out.println("welcome to collection");
        } else {
            throw new InputException("invalid command");
        }
    }

    private void shopMenuRequest(String command) throws InputException {
        ShopMenu shopMenu = ShopMenu.getInstance();
        if (currentMenu.equals(shopMenu)) {
            System.out.println("welcome to shop menu!");
            if (RequestType.SELL.setMatcher(command).find()) {

            } else if (RequestType.BUY.setMatcher(command).find()) {

            } else if (RequestType.SEARCH.setMatcher(command).find()) {

            } else if (RequestType.SEARCH_COLLECTION.setMatcher(command).find()) {

            } else if (RequestType.SHOW.setMatcher(command).find()) {
                view.minionAndSpellDeclarationInCollection();
            } else if (RequestType.SHOW_COLLECTION.setMatcher(command).find()) {

            }
        } else {
            throw new InputException("invalid command");
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
