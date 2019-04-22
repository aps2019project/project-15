package model;

import controller.Controller;
import view.View;

import java.util.ArrayList;

enum TypeOfCollectible {
    joonBaw, TIR_3_SHAKH, exir
}

public class Collectible extends Item {

    private View view = View.getInstance();

    private static ArrayList<Collectible> allCollectibles;

    public static ArrayList<Collectible> getAllCollectibles() {
        return allCollectibles;
    }

    TypeOfCollectible mode;

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


    public static void tir3shakh() {

    }


    public static void majoonMp() {

    }

    public static void majoonRoointani() {

    }

    public static void nefrinMarg() {

    }

    public static void nooshdaru() {

    }

    public static void randomDamage() {

    }

    public static void bladesOfAgility() {

    }

    public static void shamshirChini() {

    }

}
