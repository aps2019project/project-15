package model;

import java.util.ArrayList;

public class Item {

    String itemName;
    int id;
    String desc;
    String price;

    public Item() {

    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }


    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return desc;
    }

    static ArrayList<Item> items = new ArrayList<>();

    public int getId() {
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

    static Item getItemById(int id) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    static Item getItemByName(String name) {
        for (Item item : Shop.getInstance().getAllItems()) {
            if (item.getItemName().equals(name)) {
                return item;
            }
        }
        return null;
    }

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

    public void setId(int id) {
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

    @Override
    public String toString() {
        String info = "itemName: " + this.getItemName() + "\n" + "ID: " + this.getId() + "\n" + "price: " + this.getPrice() + "\n" + "desc: " + this.getDesc() + "\n";
        return info;
    }
}
