package view;

import model.DataCenter;

import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    public String command;
    private View view = View.getInstance();

    public String getNewCommand() {
        return this.command = scanner.nextLine().toLowerCase().trim();
    }

    public int enteredNum() {
        return Integer.parseInt(scanner.nextLine().trim());
    }


}
