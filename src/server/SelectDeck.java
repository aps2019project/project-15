package server;

import com.google.gson.Gson;
import controller.Controller;

public class SelectDeck implements CheckAccuracy {
    String accountName;
    String deckName;
    String message;

    public SelectDeck(String accountName, String deckName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Select Deck")) {
            return new Gson().toJson(Controller.currentAccount.getMyCollection().selectDeck(deckName));
        }
        return new Gson().toJson("false");
    }
}
