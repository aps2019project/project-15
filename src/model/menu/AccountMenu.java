package model.menu;

import controller.Controller;
import controller.DataCenter;
import model.Account;
import view.Request;

public class AccountMenu extends Menu {
    private static AccountMenu accountMenu = new AccountMenu();

    public static AccountMenu getInstance() {
        return accountMenu;
    }

    public Account register(String username, String password) {
        DataCenter dataCenter = DataCenter.getInstance();
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setLoggedIn(true);

        dataCenter.getAccounts().putIfAbsent(username, account);
        System.out.println("welcome!!!");
        return account;
    }

    public void loginFunction(String username, String password, DataCenter dataCenter) {
        Account account = dataCenter.getAccountByName(username);
        if (account != null) {
            if (!account.isLoggedIn()) {
                if (account.getPassword().equals(password)) {
                    System.out.println(account.getUsername() + " logged in!");
                } else {
                    System.out.println("your password is incorrect!");
                }
            } else {
                System.out.println("you have already logged in!");
            }
        } else {
            System.out.println("username is not valid");
        }
    }
}
