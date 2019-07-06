package client;

public class SelectDeck implements ToJson{
    String accountName;
    String deckName;
    String message;
    public SelectDeck(String accountName, String deckName){
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = "Select Deck";
    }
}
