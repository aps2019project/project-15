public class Battle {



    private static Battle ourInstance = new Battle();

    public static Battle getInstance() {
        return ourInstance;
    }

    Map map;
    private Battle() {
        map = Controller.getMap();
    }

    public void showMyMinions() {

    }

    public void showOpponentMinions() {

    }

    public void showCardInfo(String cardId) {

    }

    public void select(String cardId) {

    }

    public void moveTo(int x, int y) {

    }


    public void attack(Card myCard , Card opponentCard) {

    }

    public boolean attackComboPossible(String... opponentCardId) {
        return true;

    }

    public void attackCombo(String opponentCardId, String myCardId1, String myCardId2, String... myCard) {

    }

    public void useSpecialPower(int x , int y) {

    }

    public void showhand() {

    }
    public void insert(String CardId , int x , int y) {

    }

    public void endTurn() {

    }

}
