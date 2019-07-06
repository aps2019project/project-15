package client;

import com.google.gson.Gson;

public class Message implements ToJson {

    private String sender;
    private String message;

    private CreateAccount createAccount;
    private Login login;
    private Enter enterBattle;
    private ShowLeaderboard showLeaderboard;
    private Exit exit;
    private Sell sell;
    private Buy buy;
    private SearchInShop searchInShop;
    private CreateDeck createDeck;
    private AddToDeck addToDeck;
    private RemoveFromDeck removeFromDeck;
    private DeleteDeck deleteDeck;
    private SearchInCollection searchInCollection;
    private Validate validate;
    private SelectDeck selectDeck;
    private ShowDeck showDeck;

    public Message(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public static Message fromJson(String string) {
        return new Gson().fromJson(string, Message.class);
    }

    @Override
    public String toString() {
        return sender + ": " + message + "\n";
    }
}
