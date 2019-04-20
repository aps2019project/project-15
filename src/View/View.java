package View;

import Controller.Controller;
import Model.Account;
import Model.Collectible;

public class View {

    Account account = new Account() ;

    public void printMainMenuOfGame() {
        System.out.println("Menu: ");
        System.out.println("1.Collection");
        System.out.println("2.Shop");
        System.out.println("3.Battle");
        System.out.println("4.Exit");
        System.out.println("5.Help");
    }

    public void loginFunction() {
        while (!account.isLoggedIn()) {
            System.out.println("enter your username: ");
            account.setUsername(Controller.scanner.nextLine());
            System.out.println("enter your password: ");
            String password = Controller.scanner.nextLine();
            if (Controller.usernames.contains(account.getUsername())) {
                if (account.getPassword().equals(password)) {
                    System.out.println("logged in!");
                    account.setLoggedIn(true);
                } else {
                    System.out.println("your password is incorrect!");
                }
            } else {
                System.out.println("username is not valid!");
            }
        }
    }

    public void register() {
        System.out.print("your username: ");
        String username = Controller.scanner.nextLine();
        account.setUsername(username);

        System.out.print("your password: ");
        account.setPassword(Controller.scanner.nextLine());
        System.out.println("welcome!!!");
        account.setLoggedIn(true);
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
}
