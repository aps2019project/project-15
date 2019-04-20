import Controller.Controller;
import Model.Account;
import View.View;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1.Sign in!");
        System.out.println("2.No account? Sign Up!");
        while (!Controller.quit) {
            Model.Account account = new Account();
            switch (Integer.parseInt(Controller.scanner.nextLine())) {
                case (1) :{
                    Controller.view.loginFunction();
                    break;
                }
                case (2):{
                    Controller.view.register();
                    break;
                }
            }
            account.choosePartsOfMenu();
        }
    }

}
