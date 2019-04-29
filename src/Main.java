import controller.*;
import model.*;

import java.io.*;

import com.google.gson.*;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private static Controller controller = new Controller();

    public static void main(String[] args) {

        Gson gson;
        gson = new GsonBuilder().create();
        Minion minion = new Minion(1, 1, 1, 1, "Danial", SpecialPowerActivation.onAttack, TypeOfCounterAttack.hybrid);

        try {
            FileWriter writer = new FileWriter("cards.json");
            writer.write(gson.toJson(minion));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller controller = new Controller();
        controller.main();
    }

}
