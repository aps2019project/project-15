package model.menu;

import model.DataCenter;
import model.Account;
import view.View;

public class AccountMenu extends Menu {
    private static AccountMenu accountMenu = new AccountMenu();

    private View view = View.getInstance();

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
        view.welcome();
       // Account.saveaccount(account);
        return account;
    }

    public boolean loginFunction(String username, String password, DataCenter dataCenter) {
        Account account = dataCenter.getAccountByName(username);
        boolean loggedIn = false;
        if (account != null) {
            if (!account.isLoggedIn()) {
                if (account.getPassword().equals(password)) {
                    view.usernameLoggedIn(account);
                    loggedIn = true;
                } else {
                    view.incorrectPassword();
                }
            } else {
                view.alreadyLoggedIn();
            }
        } else {
            view.invalidUsername();
        }
        return loggedIn;
    }
}
