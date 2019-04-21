import controller.Controller;
import model.Account;
import view.Request;
import view.View;

import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Controller controller = new Controller();

        View view = View.getInstance();
        while (!Controller.quit) {
            Request request = new Request();
            System.out.println("beginning!");
            System.out.println("Sign in!");
            System.out.println("No account? Sign Up!");
            switch (request.scanner.nextLine().toLowerCase().trim()) {
                case ("sign in"): {
                    request.loginFunction();
                    account.choosePartsOfMenu();
                    break;
                }
                case ("sign up"): {

                    request.register();
                    account.choosePartsOfMenu();
                    break;
                }
            }
            System.out.println(Controller.usernames);

        }
    }

}
