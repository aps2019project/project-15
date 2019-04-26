package model.menu;

public class Menu {

    public void setPreviousMenu(Menu previousMenu) {
        this.previousMenu = previousMenu;
    }

    public Menu getPreviousMenu() {
        return previousMenu;
    }

    private Menu previousMenu;

}
