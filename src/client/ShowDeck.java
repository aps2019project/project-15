package client;

public class ShowDeck implements ToJson{
    String accountName;
    String deckName;
    String message;
    public ShowDeck(String accountName, String deckName){
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = "Show Deck";
    }
}
