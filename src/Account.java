import java.util.ArrayList;

public class Account {

    public Account() {

    }

    Game game;

    int money = 15000;
    String username;
    String password;
    boolean isSelected;
    Collection myCollection;
    int numOfWins;
    ArrayList<Deck> myDecks;
    Deck mainDeck;
    ArrayList<Card> graveYard;
    ArrayList<History> historyGames;
    private boolean startGame = false;

    public void setNewGame() {
        if (startGame) {
            Game game = new Game();
            game.players.add(this);

        }
    }

    public int getNumOfWins() {
        return numOfWins;
    }

    public void login(String username, String password) {

    }

    public void addMoney() {

    }

    public void reduceMoney() {

    }
}
