import controller.Controller;
import model.Account;
import view.Request;
import view.View;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    private  static Controller controller = new Controller();

    public static void main(String[] args) {
        controller.main();
    }

}
