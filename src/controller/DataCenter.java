package controller;

import model.*;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class DataCenter {
    private static DataCenter ourInstance = new DataCenter();
    private ArrayList<Hero> heroes;
    private ArrayList<Minion> minions;
    private ArrayList<Spell> spells;
    private ArrayList<Item> items;


    public static DataCenter getInstance() {
        return ourInstance;
    }


    public View view = View.getInstance();

    private DataCenter() {

    }

    public Card returnCardItemName(String name) {
        for (Card item : heroes) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public ArrayList<Minion> getMinions() {
        return minions;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    private Account onlineAccount;
    private HashMap<String, Account> accounts = new HashMap<>();


    public void setOnlineAccount(Account onlineAccount) {
        this.onlineAccount = onlineAccount;
    }

    public Account getAccountByName(String name) {
        for (Account account : accounts.values()
             ) {
            if (account.getUsername().equals(name)) {
                return account;
            }
        }
        return null;
    }

    public Account getOnlineAccount() {
        return onlineAccount;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }


}
