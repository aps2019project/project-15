package client;

public class Buy implements ToJson {
    String accountName;
    String cardName;
    String message;

    public Buy(String accountName, String cardName) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = "Buy";
    }
}
