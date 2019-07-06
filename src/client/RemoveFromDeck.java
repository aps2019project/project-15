package client;

public class RemoveFromDeck implements ToJson {
    String accountName;
    String deckName;
    String cardName;
    String message;

    public RemoveFromDeck(String accountName, String deckName, String cardName) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.cardName = cardName;
        this.message = "Remove to Deck";
    }
}
