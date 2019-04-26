package controller;

import model.Account;
import view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class DataCenter {
    private static DataCenter ourInstance = new DataCenter();

    public static DataCenter getInstance() {
        return ourInstance;
    }


    public View view = View.getInstance();

    private DataCenter() {

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
