package Model;

import Controller.Controller;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    public Account() {

    }

    Game game;
    Shop shop;

    int money = 15000;
    String username;
    String password;
    boolean isSelected;
    Collection myCollection;
    int numOfWins;
    ArrayList<Deck> myDecks;
    Deck mainDeck;
    ArrayList<Card> graveYard;
    ArrayList<History> historyGames;
    private boolean startGame = false;

    public void setNewGame() {
        if (startGame) {
            Game game = new Game();
            game.players.add(this);
        }
    }

    public void choosePartسOfMenu() {

        View.View.printMainMenuOfGame();

        int numOfMenuPart = Controller.scanner.nextInt();
        switch (numOfMenuPart) {
            case (1): {
                break;
            }
            case (2): {
                break;
            }
            case (3): {
                break;
            }
            case (4): {
                break;
            }
            case (5): {
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
