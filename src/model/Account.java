package model;

import controller.Controller;
import controller.DataCenter;
import view.Request;
import view.View;
import Controller.Controller;

import java.util.ArrayList;
import java.util.Collection;

public class Account {

    Battle battle = new Battle();

    public Account() {

    }

    public Account returnAccountByName(String username) {
        DataCenter dataCenter = DataCenter.getInstance();
        for (Account account : dataCenter.getAccounts().values()) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

        public boolean checkUserAndPass () {
            return AddSecondPlayer();
        }

        private boolean AddSecondPlayer () {
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

        public void setUserAndPass () {
            Controller.view.setAccount(this);
        }

        Game game;
        Shop shop;

        private int money = 15000;

        private String username;
        private String password;
        private boolean isSelected;


        private boolean loggedIn = false;
        Collection myCollection;
        private int numOfWins;
        ArrayList<Deck> myDecks;
        Deck mainDeck;
        ArrayList<Card> graveYard;
        ArrayList<History> historyGames;
        private boolean startGame = false;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNewGame() {
        if (startGame) {
            Game game = new Game();
            game.accounts.add(this);
        }
    }

    public String getUsername() {
        return username;
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

    public int getNumOfWins() {
        return numOfWins;
    }

    public void login(String username, String password) {

    }


    public void addMoney() {

    }

    public void reduceMoney() {

    }


    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
