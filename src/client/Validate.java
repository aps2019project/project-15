package client;

public class Validate implements ToJson {
    String accountName;
    String deckName;
    String message;

    public Validate(String accountName, String deckName) {
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = "Validate";
    }
}
