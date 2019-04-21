package controller;

import model.Account;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class DataCenter {

    private Account onlineAccount;
    private HashMap<String, Account> accounts = new HashMap<>();


    public void setOnlineAccount(Account onlineAccount) {
        this.onlineAccount = onlineAccount;
    }


    public Account getOnlineAccount() {
        return onlineAccount;
    }

    public HashMap<String, Account> getAccounts() {
        return accounts;
    }
}
