package server;

import com.google.gson.Gson;
import controller.Controller;

public class Validate implements CheckAccuracy{
    String accountName;
    String deckName;
    String message;

    public Validate(String accountName, String deckName, String message) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if(message.equals("Validate")){
            return new Gson().toJson(Controller.currentAccount.getMyCollection().validateDeck(deckName));
        }
        return new Gson().toJson("false");
    }
}