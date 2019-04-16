import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Collectible collectible = new Collectible();

            collectible.itemName = scanner.nextLine();
            Item.addToItems(collectible);
            collectible.using = true;
            collectible.calling();
            View.printEnterCollectibleID();
            collectible.id = scanner.nextLine();
            View.printCollectibleID(collectible);
        }
    }
}
