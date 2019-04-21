package view;

import com.sun.tools.javac.Main;
import controller.Controller;
import controller.DataCenter;
import model.Account;
import model.menu.AccountMenu;
import model.menu.Menu;

import java.util.Scanner;

public class Request {
    private Scanner scanner = new Scanner(System.in);
    private String command;

    public void getNewCommand() {
        command = scanner.nextLine().toLowerCase().trim();
    }

    public void handleRequest(Menu currentMenu, DataCenter dataCenter) throws InputException {
        if (currentMenu.equals(AccountMenu.getInstance())) {
            accountMenuRequest(dataCenter);
        } else if () {

        }
    }


    public void accountMenuRequest(DataCenter dataCenter) {
        AccountMenu accountMenu = AccountMenu.getInstance();

        if (RequestType.CREATE_ACCOUNT.setMatcher(command).find()) {
            String username = RequestType.CREATE_ACCOUNT.getMatcher().group(1);
            System.out.print("your password: ");
            getNewCommand();
            accountMenu.register(username, command, dataCenter);

        }
    }


}
