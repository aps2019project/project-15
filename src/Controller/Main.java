package Controller;

import Model.Control;

import java.util.Scanner;

public class Main {

    public static Scanner getScanner() {
        return scanner;
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {

            Model.Collectible collectible = new Model.Collectible();

            collectible.setItemName(scanner.nextLine());
            Model.Item.addToItems(collectible);
            collectible.setUsing(true);
            collectible.calling();
            Model.Control.view.printEnterCollectibleID();
            collectible.setId(scanner.nextLine());
            Control.view.printCollectibleID(collectible);
        }
    }
}
