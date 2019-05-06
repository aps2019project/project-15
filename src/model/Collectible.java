package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;
import java.util.Random;

public class Collectible extends Item {

    private View view = View.getInstance();

    private static ArrayList<Item> allCollectibles = new ArrayList<>();

    public static ArrayList<Item> getAllCollectibles() {
        return allCollectibles;
    }


    public Collectible() {
    }

    /*private static void addCollectibleToAllCollectibles() {
        model.Collectible collectible = new model.Collectible();

        collectible.setItemName(.scanner.nextLine().toLowerCase().trim());
        model.Item.addToItems(collectible);
        collectible.setUsing(true);
        Controller.view.printEnterCollectibleID();
        collectible.setId(Controller.scanner.nextLine().toLowerCase().trim());
        Controller.view.printCollectibleID(collectible);
        Controller.view.printEnterCollectibleID();
        collectible.setId(Controller.scanner.nextLine().toLowerCase().trim());
        Controller.view.printCollectibleID(collectible);
    }*/


    /*public model.Collectible getCollectible() {
        return this;
    }*/

    static Item returnItemName(String name) {
        for (Item item : allCollectibles) {
            if (item.itemName.equals(name)) {
                return item;
            }
        }
        return null;
    }
    public static ArrayList<Collectible> getRandomCollectibles(){
        ArrayList<Collectible> returns = new ArrayList<>();
        Random random = new Random();
        Collectible collectible = (Collectible) allCollectibles.get(random.nextInt(allCollectibles.size()));
        returns.add(collectible);
        int numbers = random.nextInt(5);
        for(int i = 0; i < numbers; i++){
            collectible = (Collectible) allCollectibles.get(random.nextInt(allCollectibles.size()));
            if(!returns.contains(collectible)){
                returns.add(collectible);
            }
            else {
                numbers++;
            }
        }
        return returns;
    }
}
