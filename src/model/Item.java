package model;

import java.util.ArrayList;

enum typeOfItem {
    collectible,
    usable;
}

public abstract class Item {

    String itemName;
    String id;
    String desc;
    int price;




    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }


    public int getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    static ArrayList<Item> items = new ArrayList<>();

    public String getId() {
        return id;
    }



    public int getX() {
        return x;
    }

    int x;

    public int getY() {
        return y;
    }

    int y;

    public boolean isCollected() {
        return collected;
    }

    boolean collected;

    public static ArrayList<Item> getItems() {
        return items;
    }

    boolean using = false;


    public static void addToItems(Item item) {
        items.add(item);
    }

    public boolean isUsing() {
        return using;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }

    public String returnItemName() {
        if (items.contains(this)) {
            return this.itemName;
        }
        return null;
    }

    public void showCollectables() {
        //graphics to be added
    }

    public void selectCollectable(String collectableId) {

    }

    public void showInfo() {

    }

    public void use(int x, int y) {

    }


}
