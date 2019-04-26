package model.menu;

public class MainMenu extends Menu {
    private static Menu mainMenu = new Menu();

    public static Menu getInstance() {
        return mainMenu;
    }

}
