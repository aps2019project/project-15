import Controller.Controller;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        while (true) {

            Model.Collectible collectible = new Model.Collectible();

            collectible.setItemName(Controller.scanner.nextLine());
            Model.Item.addToItems(collectible);
            collectible.setUsing(true);
            collectible.calling();
            View.View.printEnterCollectibleID();
            collectible.setId(Controller.scanner.nextLine());
            View.View.printCollectibleID(collectible);
        }
    }
}

