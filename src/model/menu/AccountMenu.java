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

    public Account register(String username, String password, DataCenter dataCenter) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setLoggedIn(true);

        dataCenter.getAccounts().putIfAbsent(username, account);
        System.out.println("welcome!!!");
        return account;
    }

    public void loginFunction() {
        while (!account.isLoggedIn()) {
            System.out.println("enter your username: ");
            account.setUsername(this.scanner.nextLine().toLowerCase().trim());
            System.out.println("enter your password: ");
            String password = this.scanner.nextLine().trim();
            if (Controller.usernames.contains(account.getUsername())) {
                if (account.getPassword().equals(password)) {
                    System.out.println("logged in!");
                    account.setLoggedIn(true);
                } else {
                    System.out.println("your password is incorrect!");
                }
            } else {
                System.out.println("username is not valid!");
            }
        }
    }

}
