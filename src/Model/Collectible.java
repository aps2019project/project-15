package Model;

import java.util.ArrayList;

enum TypeOfCollectible {
    joonBaw, TIR_3_SHAKH, exir
}

public class Collectible extends Item {

    private static ArrayList<Collectible> allCollectibles;

    public static ArrayList<Collectible> getAllCollectibles() {
        return allCollectibles;
    }

    TypeOfCollectible mode;

    public Collectible() {
    }

    @Override
    public void calling() {
        if (this.itemName != null && this.isUsing()) {
            TypeOfCollectible type = TypeOfCollectible.joonBaw;
            switch (type) {
                case exir:
                    break;
                case TIR_3_SHAKH:
                    break;
            }
            switch (this.itemName) {
                case ("joonBaw"): {
                    joonBaw();
                    break;
                }
                case ("tir3shakh"): {
                    tir3shakh();
                    break;
                }
                case ("exir"): {
                    exir();
                    break;
                }
                case ("nooshdaru"): {
                    nooshdaru();
                    break;

                }

            }
        }
    }


    /*public Model.Collectible getCollectible() {
        return this;
    }*/

    public static void joonBaw() {
        Control.view.printIncrementHealth(3);
    }

    public static void tir3shakh() {

    }

    public static void exir() {
        Control.view.printIncrementHealth(3);
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
