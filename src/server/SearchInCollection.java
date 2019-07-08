package server;

import com.google.gson.Gson;
import controller.Controller;

public class SearchInCollection implements CheckAccuracy {
    String accountName;
    String cardName;
    String message;

    public SearchInCollection(String accountName, String cardName, String message) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Search In Collection")) {
            return new Gson().toJson(Controller.currentAccount.getMyCollection().searchInCollection(cardName));
        }
        return new Gson().toJson("false");
    }
}
