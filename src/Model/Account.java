package Model;

import Controller.Controller;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    public Account() {

    }

    public void setUserAndPass() {
        Controller.view.setAccount(this);
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

        int numOfMenuPart = Controller.view.enteredNum();
        switch (numOfMenuPart) {
            case (1): {
                Controller.view.enterCllection();
                break;
            }
            case (2): {
                Controller.view.enterShop();
                break;
            }
            case (3): {
                Controller.view.enterBattle();
                Battle battle = new Battle();
                battle.chooseBattleType();
                break;
            }
            case (4): {
                Controller.view.exitMessage();
                this.setLoggedIn(false);
                Controller.view.logOutMessage();
                Controller.quit = true;
                break;
            }
            case (5): {
                Controller.view.enteredHelp();
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
