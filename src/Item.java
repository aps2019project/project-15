import java.util.ArrayList;

enum typeOfItem {
    collectible,
    usable;
}

public abstract class Item {
    String itemName;

    static ArrayList<Item> items = new ArrayList<>();
    String id;
    int x;
    int y;
    boolean collected;
    boolean using = false;


    public static void addToItems(Item item) {
        items.add(item);
    }

    public abstract void calling();

    public boolean isUsing() {
        return using;
    }


    public String returnItemName() {
        if (items.contains(this)) {
            return this.itemName;
        }
        return null;
    }

    public void showCollectables() {

    }

    public void selectCollectable(String collectableId) {

    }

    public void showInfo() {

    }

    public void use(int x, int y) {

    }


}
