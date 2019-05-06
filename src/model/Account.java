package model;

import view.View;

import java.util.ArrayList;

public class Account {

    private View view = View.getInstance();
    public boolean myTurn = false;
    private ArrayList<Card> cardsInGame = new ArrayList<>();

    private String username;
    private String password;
    private int money = 15000;
    private boolean isSelected;

    private boolean loggedIn = false;
    private Collection myCollection = new Collection();

    private Deck mainDeck = new Deck("mainDeck");

    private ArrayList<Card> graveYard = new ArrayList<>();

    private ArrayList<History> historyGames = new ArrayList<>();
    public ArrayList<Item> myCollectibles = new ArrayList<>();
    private boolean startGame = false;

    public ArrayList<Card> getCardsInGame() {
        return cardsInGame;
    }

    public void addCardInGame(Card card) {
        cardsInGame.add(card);
    }

    void removeCardInGame(Card card) {
        cardsInGame.remove(card);
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
        int numOfWins = 0;
        return numOfWins;
    }

    void addMoney(int money) {
        this.money += money;
    }

    void reduceMoney(int money) {
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

    void setMainDeck(Deck deck) throws CloneNotSupportedException {
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

    void sellCard(Card card) {
        card.owned = false;
        card.sold = true;
    }

    boolean soldCard(Card card) {
        return card.sold;
    }
    public void addToHistory(int result, int time, String opponentName){
        History history = new History(result, time, opponentName);
        historyGames.add(history);
    }

}
