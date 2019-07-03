package menu;

public class ShopMenu extends Menu {
    private static ShopMenu shopMenu = new ShopMenu();

    public static ShopMenu getInstance() {
        return shopMenu;
    }

}
