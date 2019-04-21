package model;

import controller.Controller;
import view.Request;
import view.View;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    Battle battle = new Battle();

    public Account() {

    }

    public void loginFunction() {
        while (!account.isLoggedIn()) {
            System.out.println("enter your username: ");
            account.setUsername(this.scanner.nextLine().toLowerCase().trim());
            System.out.println("enter your password: ");
            String password = this.scanner.nextLine().trim();
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

    public boolean checkUserAndPass() {
        return AddSecondPlayer();
    }
    private boolean AddSecondPlayer() {
        Account account2 = new Account();
        System.out.print("username: ");
        boolean accepted;
        this.setUsername(this.scanner.nextLine().toLowerCase().trim());
        if (!Controller.usernames.contains(this.getUsername())) {
            System.out.println("username is not valid!");
            accepted = false;
        } else {
            System.out.print("password: ");
            if (!this.getPassword().equals(this.scanner.nextLine().trim())) {
                System.out.println("wrong password!");
                accepted = false;
            } else {
                accepted = true;
            }
        }
        return accepted;
    }

    Game game;
    Shop shop;

    private int money = 15000;

    private String username;
    private String password;
    private boolean isSelected;


    boolean loggedIn = false;
    Collection myCollection;
    int numOfWins;
    ArrayList<Deck> myDecks;
    Deck mainDeck;
    ArrayList<Card> graveYard;
    ArrayList<History> historyGames;
    private boolean startGame = false;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setNewGame() {
        if (startGame) {
            Game game = new Game();
            game.players.add(this);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }


    public void choosePartsOfMenu() {
        View view = View.getInstance();
        view.printMainMenuOfGame();
        Request request = new Request();

        String menuPart = request.scanner.nextLine().toLowerCase().trim();
        switch (menuPart) {
            case ("enter collection"): {
                System.out.println("you entered Collection!");
                break;
            }
            case ("enter shop"): {
                System.out.println("you entered Shop!");
                break;
            }
            case ("enter battle"): {
                System.out.println("you entered Battle!");
                this.battle.chooseBattleType();
                break;
            }
            case ("exit"): {
                System.out.println("you entered Exit");
                this.setLoggedIn(false);
                System.out.println("you logged out");
                Controller.quit = true;
                break;
            }
            case ("help"): {
                System.out.println("you entered help!");
                view.printMainMenuOfGame();
                break;
            }
        }
    }


    public int getNumOfWins() {
        return numOfWins;
    }

    public void login(String username, String password) {

    }

    public void addMoney() {

    }

    public void reduceMoney() {

    }
}
