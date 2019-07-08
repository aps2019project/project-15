package server;

import com.google.gson.Gson;
import controller.Controller;

public class RemoveFromDeck implements CheckAccuracy {
    String accountName;
    String deckName;
    String cardName;
    String message;

    public RemoveFromDeck(String accountName, String deckName, String cardName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Remove From Deck")) {
            return new Gson().toJson(Controller.currentAccount.getMyCollection().removeCardOrItemFromDeck(cardName, deckName));
        }
        return new Gson().toJson("false");
    }
}
