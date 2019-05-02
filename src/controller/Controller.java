package controller;

import com.google.gson.Gson;
import model.*;
import model.menu.*;
import view.InputException;
import view.Request;
import view.RequestType;
import view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Request request = new Request();
    private static Controller controller = new Controller();
    private View view = View.getInstance();
    public static Menu currentMenu = new AccountMenu();
    private boolean finishGame = false;
    private static DataCenter dataCenter = DataCenter.getInstance();
    private Shop shop = Shop.getInstance();
    public static Account currentAccount = new Account();
    public static Account enemyAccount = new Account();

    private static void setCurrentMenu() {
        currentMenu = AccountMenu.getInstance();
    }

    public static Controller getInstance() {
        return controller;
    }

    public void main() {
        Request request = new Request();
        initEverything();
        while (!finishGame) {
            try {
                handleRequest(currentMenu, request.getNewCommand());
            } catch (InputException e) {
                View.getInstance().printError(e);
            }
        }
    }

    private void initEverything() {
        try {
            setCurrentMenu();
            final String[] paths = {
                    "HeroNames", "ItemNames", "SpellNames", "MinionNames",
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
            shop.addCard();
            shop.addItem();
            for (Minion minion : shop.getAllMinions()) {
                minion.setCardID(minion.id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <T> void addCard(File file, Class<T> classOfT, ArrayList<T> list) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        T card = new Gson().fromJson(reader, classOfT);
        list.add(card);
    }

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
            throw new InputException("Invalid command");
        }
    }

    private void accountMenuRequest(String command) throws InputException {
        AccountMenu accountMenu = AccountMenu.getInstance();
        if (Controller.currentMenu.equals(accountMenu)) {
            if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
                RegisterAccountFunction(accountMenu);
            } else if (RequestType.LOGIN.setMatcher(command).find()) {
                boolean ok = false;
                while (!ok) {
                    String username = RequestType.LOGIN.getMatcher().group(1);
                    view.enterPassword();
                    command = request.getNewCommand();
                    ok = accountMenu.loginFunction(username, command, dataCenter);
                }
                currentMenu = MainMenu.getInstance();
            } else if (RequestType.SHOW_LEADER_BOARD.setMatcher(command).find()) {
                leaderBoard(dataCenter);
            } else if (RequestType.SHOW_MENU.setMatcher(command).find()) {
                view.printAccountMenuOfGame();
            } else if (RequestType.EXIT.setMatcher(command).find()) {
                view.exitMessage();
                finishGame = true;
            } else if (RequestType.HELP.setMatcher(command).find()) {
                currentAccount.showMenu();
            } else {
                throw new InputException("Invalid command");
            }
        }
    }

    private void mainMenuRequest(String command) throws InputException {
        Menu mainMenu = MainMenu.getInstance();
        if (currentMenu.equals(mainMenu)) {
            if (RequestType.SAVE.setMatcher(command).find()) {
                int id;

                view.saveEverything();
            } else if (RequestType.LOGOUT.setMatcher(command).find()) {
                view.logOutMessage();
                Account account = Controller.currentAccount;
                account.setLoggedIn(false);
            } else if (RequestType.SHOW_MENU.setMatcher(command).find()) {
                view.printMainMenuOfGame();
            } else if (RequestType.ENTER_COLLECTION.setMatcher(command).find()) {
                view.enterCollection();
            } else if (RequestType.ENTER_SHOP.setMatcher(command).find()) {
                view.enterShop();
            } else if (RequestType.HELP.setMatcher(command).find()) {
                view.mainMenuHelp();
            } else if (RequestType.ENTER_BATTLE.setMatcher(command).find()) {
                view.enterBattle();
                currentMenu = BattleMenu.getInstance();
            } else if (RequestType.EXIT.setMatcher(command).find()) {
                view.exitMessage();
                currentAccount.setLoggedIn(false);
                view.logOutMessage();
                currentMenu = AccountMenu.getInstance();
            } else {
                throw new InputException("Invalid command");
            }
        }
    }

    private void battleMenuRequest(String command) throws InputException {

        BattleMenu battleMenu = BattleMenu.getInstance();
        boolean gameStarted = false;

        try {
            while (currentMenu.equals(battleMenu)) {
                if (gameStarted) {
                    System.out.println("GAME IS LOADING!!!!!!!!");
                    break;
                } else {
                    battleMenu.chooseBattleType(command);
                    gameStarted = true;
                }

            }
        } catch (InputException e) {
            throw new InputException("Invalid command");
        }
    }

    private void collectionMenuRequest(String command, DataCenter dataCenter) throws InputException {
        CollectionMenu collectionMenu = CollectionMenu.getInstance();
        Collection collection = Controller.currentAccount.getMyCollection();
        if (currentMenu.equals(collectionMenu)) {
            if (RequestType.SHOW_COLLECTION.setMatcher(command).find()) {
                view.showCollection();
            } else if (RequestType.HELP.setMatcher(command).find()) {
                collection.help();
            } else if (RequestType.EXIT_COLLECTION.setMatcher(command).find()) {
                collection.exitCollection();
            } else if (RequestType.SEARCH_COLLECTION.setMatcher(command).find()) {
                collection.searchInCollection();
            } else if (RequestType.SAVE_COLLECTION.setMatcher(command).find()) {
                collection.save();
            } else if (RequestType.ADD_COLLECTION.setMatcher(command).find()) {
                collection.cardOrItemToDeck(RequestType.ADD_COLLECTION.getMatcher().group(1), RequestType.ADD_COLLECTION.getMatcher().group(2));
                //collection.addCardToDeck(card , RequestType.ADD_COLLECTION.getMatcher().group(2));
            } else if (RequestType.REMOVE_COLLECTION.setMatcher(command).find()) {
                collection.removeCardFromDeck(RequestType.REMOVE_COLLECTION.getMatcher().group(1), RequestType.REMOVE_COLLECTION.getMatcher().group(2));
            } else if (RequestType.CREATE_DECK.setMatcher(command).find()) {
                collection.createDeck(RequestType.CREATE_DECK.getMatcher().group(1));
            } else if (RequestType.DELETE_DECK.setMatcher(command).find()) {
                collection.deleteDeck(RequestType.DELETE_DECK.getMatcher().group(1));
            } else if (RequestType.VALIDATE.setMatcher(command).find()) {
                collection.validateDeck(RequestType.VALIDATE.getMatcher().group(1));
            } else if (RequestType.SELECT_DECK.setMatcher(command).find()) {
                collection.selectDeck(RequestType.SELECT_DECK.getMatcher().group(1));
            } else if (RequestType.SHOW_ALL_DECKS.setMatcher(command).find()) {
                collection.showAllDecks();
            } else if (RequestType.SHOW_DECK.setMatcher(command).find()) {
                collection.showDeck(RequestType.SHOW_DECK.getMatcher().group(1));
            } else if (RequestType.SHOW_MENU.setMatcher(command).find()) {
                view.showCollectionMenu();
            }
        } else {
            throw new InputException("Invalid command");
        }
    }

    private void shopMenuRequest(String command) throws InputException {
        ShopMenu shopMenu = ShopMenu.getInstance();
        Shop shop = new Shop();
        if (currentMenu.equals(shopMenu)) {
            if (RequestType.SELL.setMatcher(command).find()) {
                shop.sell(RequestType.SELL.getMatcher().group(1));
            } else if (RequestType.BUY.setMatcher(command).find()) {
                shop.buy(RequestType.BUY.getMatcher().group(1));
            } else if (RequestType.SEARCH.setMatcher(command).find()) {
                shop.search(RequestType.SEARCH.getMatcher().group(1));
            } else if (RequestType.SEARCH_COLLECTION_IN_SHOW.setMatcher(command).find()) {
                shop.searchInCollection(RequestType.SELL.getMatcher().group(1));
            } else if (RequestType.SHOW.setMatcher(command).find()) {
                shop.show();
            } else if (RequestType.SHOW_COLLECTION_IN_SHOP.setMatcher(command).find()) {
                shop.showCollection();
            } else if (RequestType.HELP.setMatcher(command).find()) {
                shop.help();
            } else if (RequestType.EXIT_SHOP.setMatcher(command).find()) {
                shop.exitShop();
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

    private void RegisterAccountFunction(AccountMenu accountMenu) {
        String command;
        String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
        while (true) {
            if (!dataCenter.getAccounts().keySet().contains(username)) {
                view.enterPassword();
                command = request.getNewCommand();
                if (command.length() >= 4) {
                    Controller.currentAccount = accountMenu.register(username, command);
                    currentMenu = MainMenu.getInstance();
                    break;
                } else {
                    view.shortPassword();
                }
            } else {
                view.invalidUsername();
                break;
            }
        }
    }
}