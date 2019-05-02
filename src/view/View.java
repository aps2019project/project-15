package view;

import controller.Controller;
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

    public void printCollectibleID(Collectible collectible) {
        System.out.println(collectible.getId());
    }

    public void printIncrementHealth(int number) {
        System.out.println(number + "units added to health");
    }

    public void printCollectibleDescription(Collectible collectible) {

    }

    public void printEnterCollectibleID() {
        System.out.println("Enter the collectible ID: ");
    }

    public void showMinionsYouCanMove() {

    }

    public void showMinionsYouCanAttack() {

    }

    public void cardsYouCanInsert() {

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
        System.out.println("To remove a card from deck, type 'remove [card id] from [deck name]' ");
        System.out.println("To remove an item from deck, type 'remove [item id] from [deck name]' ");
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
        System.out.println("To see leaderboard , type 'enter leaderboard' ");
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

    public void enteredHelp() {
        System.out.println("you entered help!");
    }

    public void playerOptions() {
        System.out.println("1.Single player");
        System.out.println("2.Multi player");
    }

    public void singlePlayerChosen() {
        System.out.println("Singke player mode!");
    }

    public void multiPlayerChosen() {
        System.out.println("Multi player mode!!");
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
        for (Collectible item : Collectible.getAllCollectibles()) {
            System.out.printf("\t  %d : Name : %s _ Desc : %s _ Sell cost : %d\n", i, item.getItemName(), item.getDesc(),
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

    public void deckAlreadyExists(){
        System.out.println("A deck already exists with this name");
    }
    public void cardNotInCollection(){
        System.out.println("card is not in your collection");
    }
    public void cardIsAlreadyInDeck(){
        System.out.println("card is already in deck");
    }
    public void deckIsNotInCollection(){
        System.out.println("deck with such name does not exist in collection");
    }
    public void tooManyCardsInDeck(){
        System.out.println("there are already 20 cards in deck");
    }
    public void heroInDeck(){
        System.out.println("A hero is already in deck");
    }
    public void itemNotInCollection(){
        System.out.println("Item is not in your collection");
    }
    public void itemIsAlreadyInDeck(){
        System.out.println("item is already in deck");
    }
    public void tooManyItems(){
        System.out.println("there is already an item in this deck");
    }
    public void cardIsNotInDeck(){
        System.out.println("selected card is not in this deck");
    }
    public void itemIsNotInDeck(){
        System.out.println("selected item is not in this deck");
    }
    public void tooManyItemsInCollection() {
        System.out.println("collection includes more than three items");
    }
    public void printHeroStats(Hero hero, int i){
        System.out.printf("\t  %d : Name : %s _ AP : %d _ HP : %d _ Class : %s _Special power : %s _ Sell cost" +
                        " : %d\n", i, hero.getName(), hero.getAttackPower(), hero.getHealthLevel(), hero.getTypeOfAttack(),
                hero.getDescription(), hero.getPrice());
    }
    public void printSpellStats(Spell spell, int i){
        System.out.printf("\t  %d : Type : %s _ Name : %s _ MP : %d _ Desc : %s _ Sell cost : %d\n", i, spell.getTypeOfAttack(),
                spell.getName(), spell.getMp(), spell.getDescription(), spell.getPrice());
    }
    public void printMinionStats(Minion minion, int i){
        System.out.printf("\t  %d Type : %s _ Name : %s _ Class : %s _ AP : %d _ HP : %d _ MP : %d _ " +
                        "Special power : %s _ Sell cost : %d\n", i, minion.getTypeOfAttack(), minion.getName(), minion.getMinionType()
                , minion.getAttackPower(), minion.getHealthLevel(), minion.getMp(), minion.getDescription(), minion.getPrice());
    }
    public void printItemStats(Item item, int i){
        System.out.printf("\t  %d : Name : %s _ Desc : %s _ Sell cost : %d\n", i, item.getItemName(), item.getDesc(),
                item.getPrice());
    }
    public void heroStats(){
        System.out.println("Heroes : ");
    }
    public void itemStats(){
        System.out.println("Items : ");
    }
    public void cardStats(){
        System.out.println("Cards : ");
    }
    public void printDeckName(int i, String deckName){
        System.out.printf("%d : %s : \n", i, deckName);
    }

    public void showCollection() {

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
        for (Spell spell: dataCenter.getSpells()) {
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
    public void showMinions() {
        System.out.println("Minions: ");
        System.out.println();
        DataCenter dataCenter = DataCenter.getInstance();
        for (Minion minion : dataCenter.getMinions()){
            System.out.println(minion);
        }
        System.out.println("-----------------------------");
    }
    public void modeGame(){
        System.out.println("Single player mode!");
        System.out.println("Story Mode?");
        System.out.println("Custom Game?");
    }
    public void choseStoryMode(){System.out.println("you chose Story Mode!");}
    public void choseCustomGame(){System.out.println("you chose Custom Game!");}
    public void saveEverything() {
        System.out.println("you saved everything!");
    }

    public void enterPassword() {
        System.out.print("enter your password: ");
    }

    public void shortPassword() {
        System.out.println("password is too short! try again.");
    }

    public void invalidUsername() {
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
    public void itemOrCardIsNotInShop(){
        System.out.println("No card or item with this name exists in shop");
    }
    public void notEnoughMoney(){
        System.out.println("You don't have enough money");
    }
    public void tooManyItemsInMyCollection(){
        System.out.println("You already have 3 items in your collection");
    }
    public void itemBought(){
        System.out.println("Item is bought!");
    }
    public void cardBought(){
        System.out.println("Card is bought!");
    }
    public void notOwnThisCardOrItem(){
        System.out.println("You don't own any card or item with this name");
    }
    public void cardSold(){
        System.out.println("Card is sold");
    }

    public void itemSold(){
        System.out.println("Item is sold");
    }

    public void incorrectPassword() {
        System.out.println("your password is incorrect!");

    }

    public void alreadyLoggedIn() {
        System.out.println("you have already logged in!");
    }

    public void welcome() {
        System.out.println("welcome!!!");

    }
    public void itemAdded(){
        System.out.println("Item added to deck");
    }
    public void cardAdded(){
        System.out.println("Card added to deck");
    }

}