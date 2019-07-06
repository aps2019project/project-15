package client;

public class Sell implements ToJson {
    String accountName;
    String cardName;
    String message;

    public Sell(String accountName, String cardName) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = "save";
    }
}
