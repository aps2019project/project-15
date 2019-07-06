package client;

public class SearchInShop implements ToJson {
    String cardName;
    String message;

    public SearchInShop(String cardName) {
        this.cardName = cardName;
        this.message = "Search";
    }
}
