package server;

import com.google.gson.Gson;
import controller.Controller;
import model.Shop;

public class Sell implements CheckAccuracy{
    String accountName;
    String cardName;
    String message;

    public Sell(String accountName, String cardName, String message) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if(message.equals("Sell")){
            return new Gson().toJson(Shop.getInstance().sell(cardName));
        }
        return new Gson().toJson("false");
    }
}
