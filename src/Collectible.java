import java.util.ArrayList;

enum TypeOfCollectible {
    joonBaw, tir3shakh, exir
}

public class Collectible extends Item {

    private static ArrayList<Collectible> allCollectibles;

    public static ArrayList<Collectible> getAllCollectibles() {
        return allCollectibles;
    }

    TypeOfCollectible mode;

    public Collectible(Item item) {
        String itemName = item.returnItemName();

    }

    @Override
    public void calling(Item item) {
        if (itemName != null && item.isUsing()) {
            switch (itemName) {
                case ("joonBaw"): {
                    joonBaw();
                    break;
                }
                case ("tir3shakh"): {
                    tir3shakh();
                    break;
                }
                case("exir") :{
                    exir();
                    break;
                }
                case ("nooshdaru"):{
                    nooshdaru();
                    break;

                }

            }
        }
    }


    /*public Collectible getCollectible() {
        return this;
    }*/

    public void joonBaw() {

    }

    public void tir3shakh() {

    }

    public void exir() {

    }

    public void majoonMp() {

    }

    public void majoonRoointani() {

    }

    public void nefrinMarg() {

    }

    public void nooshdaru() {

    }

    public void randomDamage() {

    }

    public void bladesOfAgility() {

    }

    public void shamshirChini() {

    }

}
