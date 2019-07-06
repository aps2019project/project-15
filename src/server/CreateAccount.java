package server;

import com.google.gson.Gson;
import controller.Controller;
import menu.AccountMenu;
import menu.MainMenu;

public class CreateAccount implements CheckAccuracy {
    String username;
    String password;

    public CreateAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String checkAccuracy() {
        boolean lenght = false;
        if (password.length() < 4) {
            return new Gson().toJson("short password");
        }
        AccountMenu accountMenu = new AccountMenu();
        Controller.currentAccount = accountMenu.register(username, password);
        Controller.currentMenu = new MainMenu();
        return new Gson().toJson("correct");
    }
}
