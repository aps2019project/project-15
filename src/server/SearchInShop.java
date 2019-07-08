package server;

import com.google.gson.Gson;
import model.Shop;

public class SearchInShop implements CheckAccuracy {
    String cardName;
    String message;

    public SearchInShop(String cardName, String message) {
        this.cardName = cardName;
        this.message = message;
    }

    @Override
    public String checkAccuracy() {
        if (message.equals("Search In Shop")) {
            return new Gson().toJson(Shop.getInstance().search(cardName));
        }
        return new Gson().toJson("false");
    }
}
