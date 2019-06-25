package view;

import controller.Controller;
import javafx.scene.control.Alert;
import model.DataCenter;
import model.*;
import model.menu.CollectionMenu;
import model.menu.ShopMenu;
import model.Hero;


public class View {
    private static View ourInstance = new View();

    public static View getInstance() {
        return ourInstance;
    }

    private DataCenter dataCenter = DataCenter.getInstance();

    private View() {
    }

    public void printMainMenuOfGame() {
        System.out.println("Menu: ");
        System.out.println("Collection");
        System.out.println("Shop");
        System.out.println("Battle");
        System.out.println("Exit");
        System.out.println("Help");
    }

    public void printAccountMenuOfGame() {
        System.out.println("Create account");
        System.out.println("Login");
        System.out.println("Show leaderboard");
    }

    public void showCollectionMenu() {
        System.out.println("Exit\nShow\nSearch [card name]\nSearch [item name]\nSave\nCreate deck[deck name]\nDelete deck[deck name]\nAdd [card id | item id | hero id] to deck [deck name]\nRemove [card id | item id | hero id] from deck [deck name]\nValidate deck [deck name]\nSelect deck [deck name]\nShow all decks\nShow deck [deck name]\nHelp\n");
    }

    public void printError(InputException e) {
        System.out.println(e.getMessage());
    }

    public void printCollectibleID(Item collectible) {
        System.out.println(collectible.getId());
    }

    public void printItemID(Item item) {
        System.out.println(item.id);
    }

    public void showMyMinions() {
        Account account = Controller.currentAccount;
        if (account.getCardsInGame() != null) {
            for (Card card : account.getCardsInGame()) {
                showEnemyMinionsFunc(card);
            }
        } else {
            System.out.println("You have no minions in game");
        }
    }

    public void showEnemyMinion() {
        Account account = Controller.currentGame.getActiveAccount();
        if (account.equals(Controller.currentAccount)) {
            for (Card card : Controller.enemyAccount.getCardsInGame()) {
                showEnemyMinionsFunc(card);
            }
        } else {
            for (Card card : Controller.currentAccount.getCardsInGame()) {
                showEnemyMinionsFunc(card);
            }
        }
    }

    private void showEnemyMinionsFunc(Card card) {
        if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            System.out.println(card.getCardIdInGame() + " : " + card.getName() + ", health : " + card.getHealthLevel()
                    + ", location : (" + card.getCurrentBlock().getX() + ", " + card.getCurrentBlock().getY() + "), power : "
                    + card.getAttackPower());
        }
    }

    public void printCollectionHelpMenu() {
        System.out.println("To exit from collection, type 'exit' ");
        System.out.println("To show all cards and items in your collection, type 'show' ");
        System.out.println("To search a card in collection, type 'search [card name]' ");
        System.out.println("To search an item in collection, type 'search [item name]' ");
        System.out.println("To save your changes, type 'save' ");
        System.out.println("To create deck, type 'create deck [deck name]' ");
        System.out.println("To add card to deck, type 'add [card id] to deck [deck name]' ");
        System.out.println("To add item to deck, type 'add [item id] to deck [deck name]' ");
        System.out.println("To add hero to deck, type 'add [hero id] to deck [deck name]' ");
        System.out.println("To remove a card from deck, type 'remove [card id] from deck [deck name]' ");
        System.out.println("To remove an item from deck, type 'remove [item id] from deck [deck name]' ");
        System.out.println("To remove a hero from deck, type 'remove [hero id] from [deck name]' ");
        System.out.println("To check validity of a deck, type 'validate deck [deck name]' ");
        System.out.println("To choose your main deck, type 'select deck [deck name]' ");
        System.out.println("To show your all decks, type 'show all decks' ");
        System.out.println("To show a deck, type 'show deck [deck name]' ");
    }

    public void printShopHelpMenu() {
        System.out.println("To exit , please type 'exit' ");
        System.out.println("To show collection , please type 'show collection' ");
        System.out.println("To search an item in collection , please type 'search collection [item name]' ");
        System.out.println("To search a card in collection , please type 'search collection [card name]' ");
        System.out.println("To buy an item from shop , please type 'buy [item name]' ");
        System.out.println("To buy a card from shop , please type 'buy [card name]' ");
        System.out.println("To sell your card , please type 'sell [card id]' ");
        System.out.println("To sell your item , please type 'sell [item id]' ");
        System.out.println("To show all cards and items in shop , please type 'show' ");
    }

    public void accountMenuHelp() {
        System.out.println("To create account , enter 'create account [username]' ");
        System.out.println("To login , enter 'login [username]' ");
        System.out.println("To see leaderboard , type 'show leaderboard' ");
    }

    public void battleHelp() {
        System.out.println("to show game info, type 'game info'");
        System.out.println("to show your minions, type'show my minions'");
        System.out.println("to show your opponent minions, type'show opponent minions'");
        System.out.println("to show card info, type 'show card info [card ID]'");
        System.out.println("to select a card, type ' select card [card ID]'");
        System.out.println("to move to a block, type 'move to [x],[y]'");
        System.out.println("to attack, type 'attack [opponent card ID]'");
        System.out.println("to attack combo, type 'attack combo [opp card Id][your card ID][your card Id]....'");
        System.out.println("to use special power, type 'use special power [x],[y]'");
        System.out.println("to show your hand, type 'show hand'");
        System.out.println("to insert card in a blok , type 'insert [card name] in [x],[y]'");
        System.out.println("to end your turn, type 'end turn'");
        System.out.println("to show collectables , type 'show collectables'");
        System.out.println("to select a collectable, type 'select [collectable ID]'");
        System.out.println("to show info , type 'show info'");
        System.out.println("to use an item in a location, type 'use location [x],[y]'");
        System.out.println("to show next card , type 'show next card'");
        System.out.println("to enter graveyard , type 'enter graveyard'");
        System.out.println("to show a card info in graveyard , type 'show info [card ID]'");
        System.out.println("to show cards , type 'show cards'");
        System.out.println("to end game , type 'end game'");
        System.out.println("to exit , type 'exit'");
        System.out.println("to show menu , type 'show menu'");
    }


    public void enterCollection() {
        Controller.currentMenu = CollectionMenu.getInstance();
        System.out.println("you entered Collection!");
    }

    public void enterShop() {
        Controller.currentMenu = ShopMenu.getInstance();
        System.out.println("you entered Shop!");
    }

    public void enterBattle() {
        System.out.println("you entered Battle!");
        System.out.println("choose your battleType!");
    }

    public void exitMessage() {
        System.out.println("you entered Exit");
    }

    public void logOutMessage() {
        System.out.println("you logged out");
    }


    public void playerOptions() {
        System.out.println("1.Single player");
        System.out.println("2.Multi player");
    }


    public void gameDeclaration(Account account2) {
        System.out.println("game is between " + Controller.currentAccount.getUsername() + " and " + account2.getUsername());
    }

    public void listAccounts() {
        for (String item : DataCenter.getInstance().getAccounts().keySet()) {
            System.out.println(item);
        }
    }

    public void invalidAccountSelected() {
        System.out.println("invalid username entered");
    }


    public void showLeaderBoard(Account account, int num) {
        System.out.println(num + "-UserName : " + account.getUsername() + " -Wins : " + account.getNumOfWins());
    }

    public void allHeroDeclaration() {
        System.out.println("Heroes:");
        int i = 1;
        for (Hero item : Hero.getHeroes()) {
            System.out.printf("\t  %d : Name : %s _ AP : %d _ HP : %d _ Class : %s _Special power : %s _ Sell cost" +
                            " : %d\n", i, item.getName(), item.getAttackPower(), item.getHealthLevel(), item.getTypeOfAttack(),
                    item.getDescription(), item.getPrice());
            i++;
        }
    }

    public void allItemDeclaration() {
        System.out.println("Items : ");
        int i = 1;
        for (Item item : Collectible.getAllCollectibles()) {
            System.out.printf("\t  %d : Name : %s _ Desc : %s _ Sell cost : %s\n", i, item.getItemName(), item.getDesc(),
                    item.getPrice());
            i++;
        }
    }

    public void allMinionAndSpellDeclaration() {
        System.out.println("Cards : ");
        int i = 1;
        for (Spell item : Spell.getSpells()) {
            System.out.printf("\t  %d : Type : %s _ Name : %s _ MP : %d _ Desc : %s _ Sell cost : %d\n", i, item.getTypeOfAttack(),
                    item.getName(), item.getMp(), item.getDescription(), item.getPrice());
            i++;
        }
        minionDeclaration(i);
    }

    private void minionDeclaration(int i) {
        System.out.println("minions: ");
        for (Minion item : Minion.getMinions()) {
            System.out.printf("\t  %d Type : %s _ Name : %s _ Class : %s _ AP : %d _ HP : %d _ MP : %d _ " +
                            "Special power : %s _ Sell cost : %d\n", i, item.getTypeOfAttack(), item.getName(), item.getMinionType()
                    , item.getAttackPower(), item.getHealthLevel(), item.getMp(), item.getDescription(), item.getPrice());
        }
    }

    public void printCardId(Card card) {
        System.out.println(card.getCardID());
    }

    public void notFoundInCollection() {
        System.out.println("No such card or item exists in collection");
    }

    public void deckAlreadyExists() {
        alertMmessage("A deck already exists with this name");
    }

    public void cardNotInCollection() {
        alertMmessage("card is not in your collection");
    }

    public void cardIsAlreadyInDeck() {
        alertMmessage("card is already in deck");
    }

    public void deckIsNotInCollection() {
        alertMmessage("deck with such name does not exist in collection");
    }

    public void tooManyCardsInDeck() {
        alertMmessage("there are already 20 cards in deck");
    }

    public void heroInDeck() {
        alertMmessage("A hero is already in deck");
    }

    public void itemNotInCollection() {
        alertMmessage("Item is not in your collection");
    }

    public void itemIsAlreadyInDeck() {
        System.out.println("item is already in deck");
    }

    public void tooManyItems() {
        alertMmessage("there is already an item in this deck");
    }

    public void cardIsNotInDeck() {
        System.out.println("selected card is not in this deck");
    }

    public void itemIsNotInDeck() {
        System.out.println("selected item is not in this deck");
    }

    public void tooManyItemsInCollection() {
        System.out.println("collection includes more than three items");
    }

    public void printHeroStats(Hero hero, int i) {
        System.out.printf("\t  %d : Name : %s _ AP : %d _ HP : %d _ Class : %s _Special power : %s _ Sell cost" +
                        " : %d\n", i, hero.getName(), hero.getAttackPower(), hero.getHp(), hero.getTypeOfAttack(),
                hero.getDescription(), hero.getPrice());
    }

    public void printSpellStats(Spell spell, int i) {
        System.out.printf("\t  %d : Type : %s _ Name : %s _ MP : %d _ Desc : %s _ Sell cost : %d\n", i, spell.getTypeOfAttack(),
                spell.getName(), spell.getMp(), spell.getDescription(), spell.getPrice());
    }

    public void printMinionStats(Minion minion, int i) {
        System.out.printf("\t  %d : Type : %s _ Name : %s _ Class : %s _ AP : %d _ HP : %d _ MP : %d _ " +
                        "Special power : %s _ Sell cost : %d\n", i, minion.getTypeOfAttack(), minion.getName(), minion.getMinionType()
                , minion.getAttackPower(), minion.getHp(), minion.getMp(), minion.activationType, minion.getPrice());
    }

    public void printItemStats(Item item, int i) {
        System.out.printf("\t  %d : Name : %s _ Desc : %s _ Sell cost : %s\n", i, item.getItemName(), item.getDesc(),
                item.getPrice());
    }

    public void heroStats() {
        System.out.println("Heroes : ");
    }

    public void itemStats() {
        System.out.println("Items : ");
    }

    public void cardStats() {
        System.out.println("Cards : ");
    }

    public void printDeckName(int i, String deckName) {
        System.out.printf("%d : %s \n", i, deckName);
    }

    public void showCollection() {
        Controller.currentAccount.getMyCollection().showCollection();
    }

    public void showHeroesInShop() {
        DataCenter dataCenter = DataCenter.getInstance();
        System.out.println("Heroes: ");
        System.out.println();
        for (Hero hero : dataCenter.getHeroes()) {
            System.out.println(hero);
        }
        System.out.println("-----------------------------");
    }

    public void showSpells() {
        DataCenter dataCenter = DataCenter.getInstance();
        System.out.println("Spells: ");
        System.out.println();
        for (Spell spell : dataCenter.getSpells()) {
            System.out.println(spell);
        }
        System.out.println("-----------------------------");
    }

    public void showItems() {
        System.out.println("Items: ");
        DataCenter dataCenter = DataCenter.getInstance();
        for (Item item : dataCenter.getItems()) {
            System.out.println(item);
        }
        System.out.println("-----------------------------");
    }

    public void showItemsInDeck(String name) {
        Deck deck = Deck.returnDeckByName(name);
        if (deck != null) {
            if (deck.getItem() != null) {
                System.out.println("Item: ");
                System.out.println(deck.getItem());
            }
        }
    }

    public void showMinions() {
        System.out.println("Minions: ");
        System.out.println();
        DataCenter dataCenter = DataCenter.getInstance();
        for (Minion minion : dataCenter.getMinions()) {
            System.out.println(minion);
        }
        System.out.println("-----------------------------");
    }

    public void modeGame() {
        System.out.println("Single player!");
        System.out.println("Story Mode?");
        System.out.println("Custom Game?");
    }

    public void choseStoryMode() {
        System.out.println("you chose Story Mode!");
        System.out.println("you have all the 3 goals in this game");
    }

    public void choseCustomGame() {
        System.out.println("you chose Custom Game!");
    }

    public void saveEverything() {
        System.out.println("you saved everything!");
    }

    public void enterPassword() {
        System.out.print("enter your password: ");
    }

    public void shortPassword() {
        alertMmessage("password is too short! try again.");
    }

    public void invalidUsername() {
        alertMmessage("Username does not exist");
        System.out.println("username is not valid!");
    }

    public void emptyUsername() {
        System.out.println("Username is empty!");
    }

    public void exitCollection() {
        System.out.println("you exited from collection");
    }

    public void exitShop() {
        System.out.println("you exited from shop!");
    }

    public void mainMenuHelp() {
        System.out.println("To enter collection, type 'enter collection' ");
        System.out.println("To enter shop, type 'enter shop' ");
        System.out.println("To enter battle, type 'enter battle' ");
        System.out.println("To exit, type 'exit' ");
        System.out.println("To logout, type 'logout' ");
        System.out.println("To save everything, type 'save' ");
    }

    public void itemOrCardIsNotInShop() {
        alertMmessage("No card or item with this name exists in shop");
    }

    public void notEnoughMoney() {
        alertMmessage("You don't have enough money");
    }

    public void tooManyItemsInMyCollection() {
        alertMmessage("You already have 3 items in your collection");
    }

    public void itemBought() {
        System.out.println("Item is bought!");
    }

    public void cardBought() {
        System.out.println("Card is bought!");
    }

    public void notOwnThisCardOrItem() {
        alertMmessage("You don't own any card or item with this name");
    }

    public void cardSold() {
        System.out.println("Card is sold");
    }

    public void itemSold() {
        System.out.println("Item is sold");
    }

    public void incorrectPassword() {
        alertMmessage("your password is incorrect!");

    }

    private void alertMmessage(String s) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(s);
        alert.show();
    }

    public void alreadyLoggedIn() {
        alertMmessage("you have already logged in!");
        System.out.println("you have already logged in!");
    }

    public void welcome() {
        System.out.println("welcome!!!");

    }

    public void itemAdded() {
        System.out.println("Item added to deck");
    }

    public void cardAdded() {
        System.out.println("Card added to deck");
    }

    public void remainingMoney() {
        System.out.println("remaining money: " + Controller.currentAccount.getMoney());
    }

    public void deckIsNotValid() {
        System.out.println("deck is not valid");
    }

    public void showCardId(String cardID) {
        if (Integer.parseInt(cardID) != 0)
            System.out.println("card ID is: " + cardID);
    }

    public void gameIsLoading() {
        System.out.println("GAME IS LOADING!!!!!!!!");
    }

    public void usernameLoggedIn(Account account) {
        System.out.println(account.getUsername() + " logged in!");
    }

    public void singlePlayerMode() {
        System.out.println("single player mode!");
    }

    public void getSecondUsername() {
        alertMmessage("enter second player username!");
    }

    public void getSecondPassword() {
        System.out.println("enter second player password: ");
    }

    public void gameIsBetween(Account account, Account secondPlayer) {
        System.out.println("game is between " + account.getUsername() + " and " + secondPlayer.getUsername());
    }

    public void wantToAddCard(Card card) {
        System.out.println("you want to add card " + card.getName());
    }

    public void wantToAddItem(Item item) {
        System.out.println("you want to add item " + item.getItemName());
    }

    public void cardExistsInShop() {
        System.out.println("card exists in shop");
    }

    public void cardDoesntExistInShop() {
        System.out.println("card doesn't exist in shop!");
    }

    public void deckCreated(Deck deck) {
        System.out.println("deck " + deck.getName() + " created!");
    }

    public void noCardInThisDeck() {
        System.out.println("no cards in this deck!");
    }

    public void noItemsInThisDeck() {
        System.out.println("no items!");
    }

    public void itemExistsInShop() {
        System.out.println("Item exists in shop!");
    }

    public void youCanNotAttackThisCard() {
        System.out.println("You can not attack this card");
    }

    public void invalidTarget() {
        System.out.println("Invalid target");
    }

    public void cardMoved(Card card) {
        System.out.println(card.getCardIdInGame() + " moved to " + card.getCurrentBlock().getX() + " " + card.getCurrentBlock().getY());
    }

    public void showGameGoal(int num) {
        if (num == 1) {
            System.out.println("you chose 'kill opponent'");
        } else if (num == 2) {
            System.out.println("you chose 'keep flag'");
        } else if (num == 3) {
            System.out.println("you chose 'collect flags'");
        }
    }

    public void setYourGameGoal() {
        System.out.println("set your goal in game!");
        System.out.println("1. killOpponent\n2. keepFlag\n3. CollectFlags");
    }

    public void wrongCardTypeForCombo() {
        System.out.println("The chosen card is not a minion");
    }

    public void notAComboMinion() {
        System.out.println("This minion does not attack in combo");
    }

    public void cardNotInGame() {
        System.out.println("The card is not currently in game");
    }


    public void showUserOptions() {
        System.out.println("forces you can move:");
        System.out.println("forces you can attack to:");
        System.out.println("cards you can insert in map:");
    }

    public void notYourTurn() {
        System.out.println("it is not your turn!");
    }

    public void showMyCollectibles() {
        for (Item item : Controller.currentAccount.myCollectibles) {
            System.out.println(item.getItemName());
        }
    }

    public void enteredGraveYard() {
        System.out.println("you entered graveyard!");
    }

    public void showHand(Hand hand) {
        for (Card card : hand.getCardsInHand()) {
            System.out.println(card);
        }
    }

    public void invalidCardNameInGame() {
        System.out.println("Invalid card name");
    }

    public void notEnoughMana() {
        System.out.println("You don't have enough mana");
    }

    public void disarmedCard() {
        System.out.println("Card is disarmed!!");
    }

    public void cardIsStun() {
        System.out.println("Card is stunned");
    }

    public void noSuchCardInGame() {
        System.out.println("No such card in game");
    }

    public void showCardInGame(Card card) {
        if (card.getTypeOfAttack().equals(TypeOfCard.Hero)) {
            Hero hero = (Hero) card;
            System.out.printf("Hero: \n Name: %s\n Cost: %d\n Desc: %s\n", hero.getName(), hero.getPrice(), hero.getDescription());
            return;
        }
        if (card.getTypeOfAttack().equals(TypeOfCard.Minion)) {
            Minion minion = (Minion) card;
            System.out.printf("Minion:\nName: %s\nHp: %d Ap: %d Mp: %d\nRange: %d\nCombo-ability: %s\nCost: %d\nDesc: %s\n"
                    , minion.getName(), minion.getHealthLevel(), minion.getAttackPower(), minion.getMp(), minion.getRange(),
                    minion.hasComboAbility(), minion.getPrice(), minion.getDescription());
            return;
        }
        if (card.getTypeOfAttack().equals(TypeOfCard.Spell)) {
            Spell spell = (Spell) card;
            System.out.printf("Spell:\nName: %S\nMp: %d\nCost: %d\nDesc: %s\n", spell.getName(), spell.getMp(),
                    spell.getPrice(), spell.getDescription());
        }
    }

    public void deckIsBetween() {
        System.out.println("your deck is " + Controller.currentAccount.getMainDeck().getName());
    }

    public void notValidDeck() {
        alertMmessage("you have not chosen a valid deck!");
    }

    public void invalidCardId() {
        System.out.println("Invalid card ID");
    }

    public void showCurrentItem() {
        System.out.println(Controller.currentGame.currentItem);
    }

    public void quitGameRequest() {
        System.out.println("you want to quit the game!");
        System.out.println("you! loser! ha ha ha!");
    }

    public void showCardsInGraveYard() {
        for (Card card : Controller.currentAccount.getGraveYard()) {
            System.out.println(card);
        }
    }

    public void showNumOfFlags(int numOfFlags) {
        System.out.println("number of flags in game is: " + numOfFlags);
    }

    public void showNumOfCardsInDeck(int numberOfCards) {
        System.out.println("number of cards in this deck: " + numberOfCards);

    }

    public void validDeck() {
        System.out.println("deck is valid!");

    }

    public void invalidItemID() {
        alertMmessage("Item is not valid");
    }

    public void validatedDeck(String name) {
        System.out.println("deck " + name + " is validated!");

    }

    public void couldNotBeValidated(String name) {
        alertMmessage("deck " + name + " could not be validated!");

    }

    public void setMainDeck() {
        System.out.println("main deck has been set!");

    }

    public void showHeroInDeck(Deck deck) {
        int i = 1;
        for (Card item : deck.getCards()) {
            if (item.getTypeOfAttack().equals(TypeOfCard.Hero)) {
                System.out.println("Hero: ");
                item.printStats(i);
                i++;
            }
        }
    }

    public void invalidCoordinates() {
        System.out.println("Invalid coordinates");
    }

    public void noSpecialPower() {
        System.out.println("This card doesn't have any special power");
    }

    public void showNextCard() {
        Card card = Controller.currentGame.getActiveAccount().getMainDeck().getHand().getCardToBeAdded();
        if (card != null) {
            System.out.println(card);
        } else {
            System.out.println("There is no card left in deck!");
        }
    }

    public void gameWon(String winnerName) {
        System.out.println(winnerName + " won the game!");
    }

    public void showCollectible(Collectible collectible) {
        System.out.println(collectible);
    }

    public void showCollectibles() {
        for (Collectible collectible : dataCenter.getCollectibles()) {
            System.out.println(collectible);
        }
    }

    public void invalidNumber() {
        System.out.println("Invalid number!");

    }

    public void showMenuOfBattle() {
        System.out.println("Game info\nShow my minions\nshow opponent minions\nShow card info\nSelect card\nMove to (x,y)\nAttack a card\nAttack combo\nUse special power\nShow hand\nInsert card in (x,y)\nEnd turn\nShow collectables\nSelect collectable\nShow info\nUse location (x,y)\nShow next card\nEnter graveyard\nShow info of a card\nshow cards in graveyard\nHelp\nEnd game\nExit\nShow menu");
    }

    public void notInGraveYard() {
        System.out.println("this card is not in graveYard!");
    }

    public void turnChanged() {
        System.out.println(Controller.currentAccount.getUsername() + "turn");
    }

    public void rightPassword() {
        System.out.println("password is not correct!");
    }

    public void enemyWithoutMainDeck() {
        System.out.println("enemy doesn't have a main deck, log in with enemy account to choose a main deck or chose another enemy.");

    }

    public void deckNameNotEntered() {
        alertMmessage("Enter the name of the deck");
    }

    public void enterCardId() {
        alertMmessage("Enter the card's Id");
    }
    public void flagsNumber(){
        alertMmessage("enter number of flags");
    }
    public void accountNotExists(){
        alertMmessage("no account with such name exists!!!");
    }
}