package client;

public class DeleteDeck implements ToJson{
    String deckName;
    String accountName;
    String message;
    public DeleteDeck(String accountName, String deckName){
        this.accountName = accountName;
        this.deckName = deckName;
        this.message = "Delete Deck";
    }
}
