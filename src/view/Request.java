package view;

import controller.Controller;
import controller.DataCenter;
import model.Account;
import model.Battle;
import model.menu.*;

import java.util.Collection;
import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    public String command;
    private View view = View.getInstance();

    public String getNewCommand() {
        return this.command = scanner.nextLine().toLowerCase().trim();
    }

    public int enteredNum(){
        return Integer.parseInt(scanner.nextLine().trim());
    }





    public void chooseBattleType() {
        DataCenter dataCenter = DataCenter.getInstance();
        System.out.println("Single player mode!");
        System.out.println("Story Mode?");
        System.out.println("Custom Game?");
        getNewCommand();
        if (command.equals("story mode")) {
            System.out.println("you chose Story Mode!");
        } else if (command.equals("custom game")) {
            System.out.println("you chose Custom Game!");
        }
    }


}
