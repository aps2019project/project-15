package server;

import com.google.gson.Gson;
import controller.Controller;
import menu.AccountMenu;
import view.View;

public class ShowLeaderboard implements CheckAccuracy {
    String message;

    public ShowLeaderboard(String message) {
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (this.message.equals("Show Leaderboard")) {
            if (Controller.currentMenu.getClass().equals(AccountMenu.class)) {
                return new Gson().toJson(View.getInstance().showFullLeaderboard());
            }
        }
        return new Gson().toJson("false");
    }
}
