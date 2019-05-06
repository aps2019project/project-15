package view;

import model.DataCenter;

import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);

    public String getNewCommand() {
        return scanner.nextLine().toLowerCase().trim();
    }

}
