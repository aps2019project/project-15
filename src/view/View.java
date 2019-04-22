package view;

import controller.Controller;
import controller.DataCenter;
import model.*;
import model.menu.BattleMenu;
import model.menu.CollectionMenu;
import model.menu.ShopMenu;

import java.util.Comparator;


public class View {
    private static View ourInstance = new View();

    public static View getInstance() {
        return ourInstance;
    }

    private View() {
    }


    public void printMainMenuOfGame() {
        System.out.println("Menu: ");
        System.out.println("1.Collection");
        System.out.println("2.Shop");
        System.out.println("3.Battle");
        System.out.println("4.Exit");
        System.out.println("5.Help");
    }

    public void printError(InputException e) {
        System.out.println(e.getMessage());
    }

    public void printCollectibleID(Collectible collectible) {
        System.out.println("collectible Id: " + collectible.getId());
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
        Controller.currentMenu = BattleMenu.getInstance();
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
    public void listAccounts(){
        for(String item : DataCenter.getInstance().getAccounts().keySet()){
            System.out.println(item);
        }
    }
    public void invaidAccountSelected(){
        System.out.println("invalid username entered");
    }

    void showLeaderBoard(DataCenter dataCenter) {
        int num = 1;
        for (Account account : dataCenter.getAccounts().values()) {
            System.out.println(num + "-UserName : " + account.getUsername() + "-Wins : " + account.getNumOfWins());
            num++;
        }
    }
}