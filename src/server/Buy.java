package server;

import com.google.gson.Gson;
import controller.Controller;
import menu.ShopMenu;
import model.Shop;

public class Buy implements CheckAccuracy {
    String accountName;
    String cardName;
    String message;

    public Buy(String accountName, String cardName, String message) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Buy")) {
            return new Gson().toJson(Shop.getInstance().buy(cardName));
        }
        return new Gson().toJson("false");
    }
}
