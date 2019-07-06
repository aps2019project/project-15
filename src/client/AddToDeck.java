package client;

public class AddToDeck implements ToJson {
    String accountName;
    String deckName;
    String cardName;
    String message;

    public AddToDeck(String accountName, String deckName, String cardName) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.cardName = cardName;
        this.message = "Add to Deck";
    }
}
