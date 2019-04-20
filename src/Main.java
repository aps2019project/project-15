import Controller.Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Scanner getScanner() {
        return scanner;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            Model.Collectible collectible = new Model.Collectible();

            collectible.setItemName(Controller.scanner.nextLine());
            Model.Item.addToItems(collectible);
            collectible.setUsing(true);
            collectible.calling();
            Controller.view.printEnterCollectibleID();
            collectible.setId(Controller.scanner.nextLine());
            Controller.view.printCollectibleID(collectible);
            Controller.view.printEnterCollectibleID();
            collectible.setId(scanner.nextLine());
            Controller.view.printCollectibleID(collectible);
        }
    }
}
