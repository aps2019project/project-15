package model;

import view.View;

import java.util.ArrayList;

public class Account {

    private View view = View.getInstance();
    Game game = new Game();
    public boolean myTurn = false;
    private ArrayList<Card> cardsInGame = new ArrayList<>();

    private String username;
    private String password;
    private int money = 15000;
    private boolean isSelected;

    private boolean loggedIn = false;
    private Collection myCollection = new Collection();
    private int numOfWins;

    private Deck mainDeck;

    private ArrayList<Card> graveYard = new ArrayList<>();

    ArrayList<History> historyGames = new ArrayList<>();
    public ArrayList<Item> myCollectibles = new ArrayList<>();
    private boolean startGame = false;
    public ArrayList<Card> myCardsInMap = new ArrayList<>();

    public ArrayList<Card> getCardsInGame() {
        return cardsInGame;
    }

    public void addCardInGame(Card card) {
        cardsInGame.add(card);
    }

    void removeCardInGame(Card card) {
        cardsInGame.remove(card);
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

    public ArrayList<Card> getGraveYard() {
        return graveYard;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Deck getMainDeck() {
        return mainDeck;
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

    public void setMainDeck(Deck deck) throws CloneNotSupportedException {
        for (Card card : deck.getCards()) {
            Card mainDeckCard = card.clone();
            mainDeck.addCard(mainDeckCard);
        }
        if (deck.getItem() != null) {
            Item mainDeckItem = deck.getItem().clone();
            mainDeck.addItem(mainDeckItem);
        }
    }

    public void showMenu() {
        view.accountMenuHelp();
    }

    public Collection getMyCollection() {
        return myCollection;
    }

    public void sellCard(Card card) {
        card.owned = false;
        card.sold = true;
    }

    public boolean soldCard(Card card) {
        return card.sold;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
