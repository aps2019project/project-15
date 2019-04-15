import java.util.ArrayList;

enum typeOfItem {
    collectible,
    usable;
}

public abstract class Item {
    String itemName;

    String id;
    int x;
    int y;
    boolean collected;
    boolean using = false;

    public boolean isUsing() {
        return using;
    }

    ArrayList<Item> items;

    public Item() {

    }

    public String returnItemName() {
        if (items.contains(this)) {
            return this.itemName;
        }
        return null;
    }

    public abstract void calling(Item item);

    public void showCollectables() {

    }

    public void selectCollectable(String collectableId) {

    }

    public void showInfo() {

    }

    public void use(int x, int y) {

    }


}
