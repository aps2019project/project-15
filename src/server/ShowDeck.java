package server;

import com.google.gson.Gson;
import controller.Controller;
import model.Deck;

public class ShowDeck implements CheckAccuracy {
    String accountName;
    String deckName;
    String message;

    public ShowDeck(String accountName, String deckName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Show Deck")) {
            return new Gson().toJson(Deck.returnDeckByName(deckName));
        }
        return new Gson().toJson("false");
    }
}
