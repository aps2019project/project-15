package server;

import com.google.gson.Gson;
import controller.Controller;
import model.Account;
import model.DataCenter;

public class Login implements CheckAccuracy{
    String username;
    String password;
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public String checkAccuracy() {
        Account account = DataCenter.getInstance().getAccountByName(username);
        boolean loggedIn = false;
        if (account != null) {
            if (!account.isLoggedIn()) {
                if (account.getPassword().equals(password)) {
                    loggedIn = true;
                    Controller.currentAccount = account;
                    return new Gson().toJson("correct");
                } else {
                    return new Gson().toJson("incorrect password");
                }
            } else {
                return new Gson().toJson("already logged in");
            }
        }
        return new Gson().toJson("invalid username");
    }
}
