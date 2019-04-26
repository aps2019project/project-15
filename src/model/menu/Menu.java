package model.menu;

public class Menu {

    static Menu mainMenu = new Menu();

    public static Menu getInstance() {
        return mainMenu;
    }


    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    public Menu getPreviousMenu() {
        return previousMenu;
    }

    private Menu previousMenu;

}
