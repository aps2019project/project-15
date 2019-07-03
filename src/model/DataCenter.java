package model;

import view.View;

import java.util.ArrayList;
import java.util.HashMap;

public class DataCenter {
    private static DataCenter ourInstance = new DataCenter();
    private ArrayList<Hero> heroes = new ArrayList<>();
    private ArrayList<Minion> minions = new ArrayList<>();
    private ArrayList<Spell> spells = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Collectible> collectibles = new ArrayList<>();
    private HashMap<String, Account> accounts = new HashMap<>();


    public static DataCenter getInstance() {
        return ourInstance;
    }
    public View view = View.getInstance();


    private DataCenter() {

    }

    public ArrayList<Collectible> getCollectibles() {
        return collectibles;
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

    public Account getAccountByName(String name) {
        for (Account account : accounts.values()
        ) {
            if (account.getUsername() != null) {
                if (account.getUsername().equals(name)) {
                    return account;
                }
            }
        }
        return null;
    }

    public void setAccounts(HashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void putAccount(Account account) {
        accounts.put(account.getUsername(), account);
    }


}
