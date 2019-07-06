package client;

public class SearchInCollection implements ToJson {
    String accountName;
    String cardName;
    String message;

    public SearchInCollection(String accountName, String cardName) {
        this.accountName = accountName;
        this.cardName = cardName;
        this.message = "Search Collection";
    }
}
