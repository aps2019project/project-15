package server;

import com.google.gson.Gson;
import controller.Controller;

public class DeleteDeck implements CheckAccuracy{
    String deckName;
    String accountName;
    String message;

    public DeleteDeck(String accountName, String deckName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if(message.equals("Delete Deck")){
            return new Gson().toJson(Controller.currentAccount.getMyCollection().deleteDeck(deckName));
        }
        return new Gson().toJson("false");
    }
}
