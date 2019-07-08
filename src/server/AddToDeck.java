package server;

import com.google.gson.Gson;
import controller.Controller;

public class AddToDeck implements CheckAccuracy {
    String accountName;
    String deckName;
    String cardName;
    String message;

    public AddToDeck(String accountName, String deckName, String cardName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Add To Deck")) {
            return new Gson().toJson(Controller.currentAccount.getMyCollection().cardOrItemToDeck(cardName, deckName));
        }
        return new Gson().toJson("false");
    }
}
