package Model;

import Controller.Controller;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    public Account() {

    }

    public void setUserAndPass() {
        System.out.print("username: ");
        this.setUsername(Controller.scanner.nextLine());
        System.out.print("password: ");
        this.setPassword(Controller.scanner.nextLine());
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

        Controller.view.printMainMenuOfGame();

        int numOfMenuPart = Integer.parseInt(Controller.scanner.nextLine());
        switch (numOfMenuPart) {
            case (1): {
                System.out.println("you entered Collection!");
                break;
            }
            case (2): {
                System.out.println("you entered Shop!");
                break;
            }
            case (3): {
                System.out.println("you entered Battle!");
                Battle battle = new Battle();
                battle.chooseBattleType();
                break;
            }
            case (4): {
                System.out.println("you entered Exit");
                this.setLoggedIn(false);
                System.out.println("you logged out");
                Controller.quit = true;
                break;
            }
            case (5): {
                System.out.println("you entered help!");
                Controller.view.printMainMenuOfGame();
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
