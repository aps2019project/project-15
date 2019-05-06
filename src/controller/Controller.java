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
    private boolean gameStarted = false;
    private boolean exit = false;
    public static Game currentGame;

    private void setCurrentMenu() {
        currentMenu = AccountMenu.getInstance();
    }

    public static Controller getInstance() {
        return controller;
    }

    public void main() {
        Request request = new Request();
        initEverything();
        view.showMinions();
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
            addJSONFiles();
            shop.addCard();
            shop.addItem();
            setAllCardsAndItemsID();
            setTypeOfAttacksForAllCards();
            collectibleAdder();
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

    private void battleMenuRequest(String command) throws InputException {

        BattleMenu battleMenu = BattleMenu.getInstance();
        try {
            if (gameStarted) {
                view.gameIsLoading();
                Game game = new Game();
                currentGame = game;
                currentAccount.setGame(game);
                enemyAccount.setGame(currentGame);
                gameFunction(game);
            } else {
                if (Controller.currentAccount.getMainDeck().validated) {
                    System.out.println("your deck is " + currentAccount.getMainDeck().getName());
                    battleMenu.chooseBattleType(command);
                    gameStarted = true;
                } else {
                    System.out.println("you have not chosen a valid deck!");
                    currentMenu = MainMenu.getInstance();
                }
            }
        } catch (
                InputException e) {
            throw new InputException("Invalid command");
        }
    }

    private void gameFunction(Game game) throws InputException {
        while (!exit) {
            String command = request.getNewCommand();
            if (currentAccount.myTurn) {
            } else {
                view.notYourTurn();
            }
            if (RequestType.GAME_INFO.setMatcher(command).find()) {
                if (game.getMode().equals(ModeOfGame.killOpponent)) {

                } else if (game.getMode().equals(ModeOfGame.KeepFlag)) {

                } else if (game.getMode().equals(ModeOfGame.CollectFlags)) {

                }
            } else if (RequestType.SHOW_MY_MINIONS.setMatcher(command).find()) {
/*
                    for (Minion minion : )
*/
            } else if (RequestType.SHOW_OPP_MINIONS.setMatcher(command).find()) {

            } else if (RequestType.SHOW_CARD_INFO.setMatcher(command).find()) {

            } else if (RequestType.SELECT_CARD.setMatcher(command).find()) {
                Card card = Card.returnCardByName(RequestType.SELECT_CARD.getMatcher().group(1));
                if (card != null) {
                    currentGame.currentCard = card;
                } else {
                    System.out.println("Invalid card ID");
                }
            } else if (RequestType.MOVE_TO.setMatcher(command).find()) {

            } else if (RequestType.ATTACK_OPP.setMatcher(command).find()) {
                String name = RequestType.ATTACK_OPP.getMatcher().group(1);
                Card card = Card.returnCardByName(name);
                if (card != null) {
                    currentGame.currentCard.attack(card);
                }
            } else if (RequestType.ATTACH_COMBO.setMatcher(command).find()) {

            } else if (RequestType.USE_SPECIAL_POWER.setMatcher(command).find()) {

            } else if (RequestType.SHOW_HAND.setMatcher(command).find()) {

            } else if (RequestType.INSERT_CARD_IN_BLOCK.setMatcher(command).find()) {

                String name = RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(1);
                int x = Integer.parseInt(RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(2));
                int y = Integer.parseInt(RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(3));

            } else if (RequestType.END_TURN.setMatcher(command).find()) {
                currentAccount.myTurn = false;
            } else if (RequestType.SHOW_COLLECTABLES.setMatcher(command).find()) {
                view.showMyCollectibles();
            } else if (RequestType.SELECT_COLLECTABLE.setMatcher(command).find()) {
                String name = RequestType.SELECT_COLLECTABLE.getMatcher().group(1);
                game.currentItem = Item.getItemByName(name);
            } else if (RequestType.SHOW_INFO.setMatcher(command).find()) {
                System.out.println(currentGame.currentItem);
            } else if (RequestType.USE_LOCATION.setMatcher(command).find()) {

            } else if (RequestType.SHOW_NEXT_CARD.setMatcher(command).find()) {

            } else if (RequestType.ENTER_GRAVEYARD.setMatcher(command).find()) {
                view.enteredGraveYard();
                graveYardFunction();
            } else if (RequestType.SHOW_INFO_CARD_ID.setMatcher(command).find()) {

            } else if (RequestType.END_GAME.setMatcher(command).find()) {

            } else if (RequestType.SHOW_MENU.setMatcher(command).find()) {

            } else if (RequestType.EXIT.setMatcher(command).find()) {
                exit = true;
                break;
            } else if (RequestType.HELP.setMatcher(command).find()) {
                view.showUserOptions();
            } else if (RequestType.HELP_MENU.setMatcher(command).find()) {
                view.battleHelp();
            } else if (RequestType.QUIT_GAME.setMatcher(command).find()) {
                System.out.println("you want to quit the game!");
                System.out.println("you! loser! ha ha ha!");
                currentMenu = MainMenu.getInstance();
            }
        }
    }

    private void collectionMenuRequest(String command, DataCenter dataCenter) throws InputException {
        Collection collection = Controller.currentAccount.getMyCollection();
        if (RequestType.SHOW_COLLECTION.setMatcher(command).find()) {
            view.showCollection();
        } else if (RequestType.HELP.setMatcher(command).find()) {
            collection.help();
        } else if (RequestType.EXIT_COLLECTION.setMatcher(command).find()) {
            collection.exitCollection();
        } else if (RequestType.SEARCH_COLLECTION.setMatcher(command).find()) {
            String name = RequestType.SEARCH_COLLECTION.getMatcher().group(1);
            collection.searchInCollection(name);
        } else if (RequestType.SAVE_COLLECTION.setMatcher(command).find()) {
            collection.save();
        } else if (RequestType.REMOVE_COLLECTION.setMatcher(command).find()) {
            collection.removeCardOrItemFromDeck(RequestType.REMOVE_COLLECTION.getMatcher().group(1), RequestType.REMOVE_COLLECTION.getMatcher().group(2));
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
            collection.showMenu();
        } else if (RequestType.ADD_COLLECTION.setMatcher(command).find()) {
            if (RequestType.ADD_COLLECTION.getMatcher().group(1).matches("[\\d]+")) {
                collection.cardOrItemToDeck(RequestType.ADD_COLLECTION.getMatcher().group(1), RequestType.ADD_COLLECTION.getMatcher().group(2));
            }
        } else {
            throw new InputException("Invalid command");
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

    private void setTypeOfAttacksForAllCards() {
        for (Hero hero : dataCenter.getHeroes()) {
            hero.setTypeOfAttack();
        }
        for (Minion minion : dataCenter.getMinions()) {
            minion.setTypeOfAttack();
        }
        for (Spell spell : dataCenter.getSpells()) {
            spell.setTypeOfAttack();
        }
    }

    private void setAllCardsAndItemsID() {
        for (Minion minion : shop.getAllMinions()) {
            minion.setCardID(minion.id);
        }
        for (Hero hero : shop.getAllHeroes()) {
            hero.setCardID(hero.id);
        }
        for (Spell spell : shop.getAllSpells()) {
            spell.setCardID(spell.id);
        }
        for (Item item : shop.getAllItems()) {
            item.setId(item.id);
        }
    }

    private void accountMenuRequest(String command) throws InputException {
        AccountMenu accountMenu = AccountMenu.getInstance();
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

    private void mainMenuRequest(String command) throws InputException {
        if (RequestType.SAVE.setMatcher(command).find()) {
            //todo
            view.saveEverything();
        } else if (RequestType.LOGOUT.setMatcher(command).find()) {
            view.logOutMessage();
            currentMenu = AccountMenu.getInstance();
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
            currentMenu = AccountMenu.getInstance();
        } else {
            throw new InputException("Invalid command");
        }

    }

    private void shopMenuRequest(String command) throws InputException {
        Shop shop = new Shop();
        if (RequestType.SELL.setMatcher(command).find()) {
            shop.sell(RequestType.SELL.getMatcher().group(1));
        } else if (RequestType.BUY.setMatcher(command).find()) {
            shop.buy(RequestType.BUY.getMatcher().group(1));
        } else if (RequestType.SEARCH.setMatcher(command).find()) {
            String cardID = shop.search(RequestType.SEARCH.getMatcher().group(1));
            if (cardID != null) {
                view.showCardId(cardID);
            }
        } else if (RequestType.SEARCH_COLLECTION_IN_SHOW.setMatcher(command).find()) {
            shop.searchInCollection(RequestType.SEARCH_COLLECTION_IN_SHOW.getMatcher().group(1));
        } else if (RequestType.SHOW.setMatcher(command).find()) {
            shop.show();
        } else if (RequestType.SHOW_COLLECTION_IN_SHOP.setMatcher(command).find()) {
            shop.showCollection();
        } else if (RequestType.HELP.setMatcher(command).find()) {
            shop.help();
        } else if (RequestType.EXIT_SHOP.setMatcher(command).find()) {
            shop.exitShop();
        } else {
            throw new InputException("Invalid command");
        }
    }

    private void collectibleAdder() {
        for (Item item : dataCenter.getItems()) {
            if (item.getPrice().equals("collectible")) {
                Collectible.getAllCollectibles().add(item);
            }
        }
    }

    private void addJSONFiles() throws IOException {
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
    }

    private void graveYardFunction() throws InputException {
        String command = request.getNewCommand();
        if (RequestType.SHOW_CARDS_GRAVEYARD.setMatcher(command).find()) {
            for (Card card : currentAccount.getGraveYard()) {
                System.out.println(card);
            }
        } else {
            throw new InputException("Invalid command");
        }
    }
}