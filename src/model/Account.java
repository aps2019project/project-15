package model;

import view.View;

import java.util.ArrayList;

public class Account {

    Battle battle = new Battle();
    private View view = View.getInstance();
    ArrayList<Deck> allDecks = new ArrayList<>();
    Game game;
    Shop shop;

    private String username;
    private String password;
    private int money = 15000;
    private boolean isSelected;

    private boolean loggedIn = false;
    private model.Collection myCollection;
    private int numOfWins;
    ArrayList<Deck> myDecks;
    Deck mainDeck;
    ArrayList<Card> graveYard;
    ArrayList<History> historyGames;
    private boolean startGame = false;

    public Account() {

    }

    public static Account returnAccountByName(String username) {
        DataCenter dataCenter = DataCenter.getInstance();
        for (Account account : dataCenter.getAccounts().values()) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }
        return null;
    }

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


    public void addMoney(int money) {
        this.money += money;
    }

    public void reduceMoney(int money) {
        this.money -= money;
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

    public void setMainDeck(Deck deck) {
        this.mainDeck = deck;
    }

    public void showMenu() {
        view.accountMenuHelp();
    }

    public model.Collection getMyCollection() {
        return myCollection;
    }

    public void sellCard(Card card) {
        card.owned = false;
        card.sold = true;
    }

    public boolean soldCard(Card card) {
        return card.sold;
    }
}
