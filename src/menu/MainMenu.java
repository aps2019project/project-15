package menu;

import view.View;

public class MainMenu extends Menu {
    private static Menu mainMenu = new Menu();

    public static Menu getInstance() {
        return mainMenu;
    }

    private View view = View.getInstance();

}
