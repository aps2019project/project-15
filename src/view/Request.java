package view;

import com.sun.tools.javac.Main;
import controller.Controller;
import model.Account;

import java.util.Scanner;

public class Request {
    public Scanner scanner = new Scanner(System.in);

    public Account account = new Account();

    public String getCommand() {
        return this.scanner.nextLine().toLowerCase().trim();
    }

    public void register() {
        System.out.print("your username: ");
        String username = this.scanner.nextLine().toLowerCase().trim();
        account.setUsername(username);
        System.out.print("your password: ");
        account.setPassword(this.scanner.nextLine().trim());
        Controller.players.add(account);
        Controller.usernames.add(account.getUsername());
        System.out.println("welcome!!!");
        account.setLoggedIn(true);
    }
}
