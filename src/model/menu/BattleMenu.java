package model.menu;

public class BattleMenu extends Menu {

    private static BattleMenu battleMenu = new BattleMenu();
    public static BattleMenu getInstance() {
        return battleMenu;
    }

    public void chooseBattleType(String command) {
        if (command.equals("single player")) {
            System.out.println("single player mode!");
        }
        else if (command.equals("multi player")) {

        }
    }
}
