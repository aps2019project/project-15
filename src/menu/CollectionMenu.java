package menu;

public class CollectionMenu extends Menu {
    private static CollectionMenu collectionMenu = new CollectionMenu();

    public static CollectionMenu getInstance() {
        return collectionMenu;
    }
}
