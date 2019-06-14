package controller;

import com.google.gson.Gson;
import model.*;
import model.menu.*;
import model.menu.BattleMenu;
import view.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    private Request request = new Request();
    private static Controller controller = new Controller();
    private View view = View.getInstance();
    public static Menu currentMenu = new model.menu.AccountMenu();
    private boolean finishGame = false;
    private static DataCenter dataCenter = DataCenter.getInstance();
    private Shop shop = Shop.getInstance();
    public static Account currentAccount = new Account();
    public static Account enemyAccount = new Account();
    private boolean gameStarted = false;
    private boolean exit = false;
    public static Game currentGame;

    public static Controller getInstance() {
        return controller;
    }

    public void main() {
        Request request = new Request();
        initEverything();
        while (!finishGame) {
            try {
                System.out.println(currentMenu);
                handleRequest(currentMenu, request.getNewCommand());
            } catch (InputException e) {
                View.getInstance().printError(e);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
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

    private void handleRequest(Menu currentMenu, String command) throws InputException, CloneNotSupportedException {
        if (currentMenu.equals(MainMenu.getInstance())) {
            mainMenuRequest(command);
        } else if (currentMenu.equals(model.menu.AccountMenu.getInstance())) {
            accountMenuRequest(command);
        } else if (currentMenu.equals(BattleMenu.getInstance())) {
            battleMenuRequest(command);
        } else if (currentMenu.equals(model.menu.CollectionMenu.getInstance())) {
            collectionMenuRequest(command);
        } else if (currentMenu.equals(ShopMenu.getInstance())) {
            shopMenuRequest(command);
        } else {
            throw new InputException("Invalid command");
        }
    }

    private void battleMenuRequest(String command) throws InputException {

        BattleMenu battleMenu = BattleMenu.getInstance();
        try {
            Game game = new Game();
            view.gameIsLoading();
            currentGame = game;
            if (Controller.currentAccount.getMainDeck() != null && Controller.currentAccount.getMainDeck().validated) {
                view.deckIsBetween();
                view.playerOptions();
                battleMenu.chooseBattleType(game, command);
                gameStarted = true;
                gameFunction(game);
            } else {
                view.notValidDeck();
                currentMenu = MainMenu.getInstance();
            }

        } catch (
                InputException e) {
            throw new InputException("Invalid command");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    private void gameFunction(Game game) throws InputException, CloneNotSupportedException {
        while (!exit) {
            String command = request.getNewCommand();
            //if (currentAccount.myTurn) {
            if (RequestType.GAME_INFO.setMatcher(command).find()) {
                if(game == null){
                    System.out.println("!!!");
                }
                if (game.getMode().equals(ModeOfGame.killOpponent)) {
                    System.out.println(game.player1Hp);
                    System.out.println(game.player2Hp);
                }
            } else if (RequestType.SHOW_MY_MINIONS.setMatcher(command).find()) {
                showMinionsFunction(currentAccount);
            } else if (RequestType.SHOW_OPP_MINIONS.setMatcher(command).find()) {
                showMinionsFunction(enemyAccount);
            } else if (RequestType.SHOW_CARD_INFO.setMatcher(command).find()) {
                game.showCardInfo(RequestType.SHOW_CARD_INFO.getMatcher().group(1));
            } else if (RequestType.SELECT_CARD.setMatcher(command).find()) {
                Card card = Card.returnCardByName(RequestType.SELECT_CARD.getMatcher().group(1));
                if (card != null) {
                    currentGame.currentCard = card;
                } else {
                    view.invalidCardId();
                }
            } else if (RequestType.MOVE_TO.setMatcher(command).find()) {
                moveCardFunction();
            } else if (RequestType.ATTACK_OPP.setMatcher(command).find()) {
                attackFunction();
            } else if (RequestType.ATTACK_COMBO.setMatcher(command).find()) {
                String oppId, id, id2;
                oppId = RequestType.ATTACK_COMBO.getMatcher().group(1);
                id = RequestType.ATTACK_COMBO.getMatcher().group(2);
                id2 = RequestType.ATTACK_COMBO.getMatcher().group(3);
                game.attackCombo(oppId, id, id2);
            } else if (RequestType.USE_SPECIAL_POWER.setMatcher(command).find()) {
                useSpecialPower();
            } else if (RequestType.SHOW_HAND.setMatcher(command).find()) {
                currentGame.showHand();
            } else if (RequestType.INSERT_CARD_IN_BLOCK.setMatcher(command).find()) {
                cardInGameInsert(game);
            } else if (RequestType.END_TURN.setMatcher(command).find()) {
/*
                currentAccount.myTurn = false;
*/
                view.turnChanged();
                game.switchTurn();
            } else if (RequestType.SHOW_COLLECTABLES.setMatcher(command).find()) {
                view.showMyCollectibles();
            } else if (RequestType.SELECT_COLLECTABLE.setMatcher(command).find()) {
                String name = RequestType.SELECT_COLLECTABLE.getMatcher().group(1);
                game.currentItem = Item.getItemByName(name);
            } else if (RequestType.SHOW_INFO.setMatcher(command).find()) {
                view.showCurrentItem();
            } else if (RequestType.USE_LOCATION.setMatcher(command).find()) {
                int x = Integer.parseInt(RequestType.USE_LOCATION.getMatcher().group(1));
                int y = Integer.parseInt(RequestType.USE_LOCATION.getMatcher().group(2));
                game.useItem(currentGame.currentItem, x, y);
            } else if (RequestType.SHOW_NEXT_CARD.setMatcher(command).find()) {
                view.showNextCard();
            } else if (RequestType.ENTER_GRAVEYARD.setMatcher(command).find()) {
                view.enteredGraveYard();
                graveYardFunction();
            } else if (RequestType.SHOW_INFO_CARD_ID.setMatcher(command).find()) {
                if (currentAccount.getGraveYard().contains(currentGame.currentCard)) {
                    System.out.println(currentGame.currentCard);
                } else {
                    view.notInGraveYard();
                }
            } else if (RequestType.END_GAME.setMatcher(command).find()) {
                if (currentGame.isFinishedGame()) {
                    currentMenu = MainMenu.getInstance();
                }
            } else if (RequestType.SHOW_MENU.setMatcher(command).find()) {
                view.showMenuOfBattle();
            } else if (RequestType.EXIT.setMatcher(command).find()) {
                exit = true;
                return;
            } else if (RequestType.HELP.setMatcher(command).find()) {
                game.help();
            } else if (RequestType.HELP_MENU.setMatcher(command).find()) {
                view.battleHelp();
            } else if (RequestType.QUIT_GAME.setMatcher(command).find()) {
                view.quitGameRequest();
                currentMenu = MainMenu.getInstance();
            }
            //} //else {
            //view.notYourTurn();
            //view.turnChanged();
            //currentAccount.myTurn = true;
            //}
        }
    }

    private void useSpecialPower() {
        currentGame.useSpecialPower(Integer.parseInt(RequestType.USE_SPECIAL_POWER.getMatcher().group(1)),
                Integer.parseInt(RequestType.USE_SPECIAL_POWER.getMatcher().group(1)));
    }

    private void moveCardFunction() {
        Card card = currentGame.currentCard;
        int x = Integer.parseInt(RequestType.MOVE_TO.getMatcher().group(1));
        int y = Integer.parseInt(RequestType.MOVE_TO.getMatcher().group(2));
        currentGame.moveTo(card, x, y);
    }

    private void attackFunction() throws CloneNotSupportedException {
        String name = RequestType.ATTACK_OPP.getMatcher().group(1);
        Card card = Card.returnCardByName(name);
        if (card != null) {
            currentGame.currentCard.attack(card);
        }
    }

    private void cardInGameInsert(Game game) {
        String name = RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(1);
        int x = Integer.parseInt(RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(2));
        int y = Integer.parseInt(RequestType.INSERT_CARD_IN_BLOCK.getMatcher().group(3));
        game.addCardsToGame(name, x, y);
        Card card = Card.returnCardByName(name);
        currentAccount.addCardInGame(card);
    }

    private void showMinionsFunction(Account account) {
        for (Card card : account.getCardsInGame()) {
            if (dataCenter.getMinions().contains(card)) {
                System.out.println(card);
            }
        }
    }

    private void collectionMenuRequest(String command) throws InputException, CloneNotSupportedException {
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

    private void RegisterAccountFunction(model.menu.AccountMenu accountMenu) {
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
        model.menu.AccountMenu accountMenu = model.menu.AccountMenu.getInstance();
        if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
            RegisterAccountFunction(accountMenu);
        } else if (RequestType.LOGIN.setMatcher(command).find()) {
            boolean ok = false;
            while (!ok) {
                String username = RequestType.LOGIN.getMatcher().group(1);
                view.enterPassword();
                command = request.getNewCommand();
                ok = accountMenu.loginFunction(username, command);
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
            currentMenu = model.menu.AccountMenu.getInstance();
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
            currentMenu =model.menu.AccountMenu.getInstance();
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
        for (Collectible collectible : dataCenter.getCollectibles()) {
            Collectible.addToCollectibles(collectible);
        }
    }

    private void addJSONFiles() throws IOException {
        final String[] paths = {
                "HeroNames", "ItemNames", "SpellNames", "MinionNames", "Collectibles",
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
                    } else if (path.contains("Collectibles")) {
                        addCard(file, Collectible.class, dataCenter.getCollectibles());
                    }
                }
            }
        }
    }

    private void graveYardFunction() throws InputException {
        String command = request.getNewCommand();
        if (RequestType.SHOW_CARDS_GRAVEYARD.setMatcher(command).find()) {
            view.showCardsInGraveYard();
        } else {
            throw new InputException("Invalid command");
        }
    }

    private void setCurrentMenu() {
        currentMenu = model.menu.AccountMenu.getInstance();
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }
}