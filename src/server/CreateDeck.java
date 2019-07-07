package server;

import com.google.gson.Gson;
import controller.Controller;

public class CreateDeck implements CheckAccuracy{
    String deckName;
    String accountName;
    String message;

    public CreateDeck(String accountName, String deckName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if(message.equals("Create Deck")){
            return new Gson().toJson(Controller.currentAccount.getMyCollection().createDeck(deckName));
        }
        return new Gson().toJson("false");
    }
}
