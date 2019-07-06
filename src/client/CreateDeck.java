package client;

public class CreateDeck implements ToJson {
    String deckName;
    String accountName;
    String message;

    public CreateDeck(String accountName, String deckName) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = "Create Deck";
    }
}
