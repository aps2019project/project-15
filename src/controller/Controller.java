package controller;

import com.sun.tools.javac.Main;
import model.Account;
import model.Map;
import model.menu.AccountMenu;
import model.menu.Menu;
import view.InputException;
import view.Request;
import view.View;

import java.util.*;

public class Controller {

    private DataCenter dataCenter = DataCenter.getInstance();
    public static Menu currentMenu = new Menu();

    public static void setCurrentMenu() {
        currentMenu = AccountMenu.getInstance();
    }

    public static Account currentAccount = new Account();

    public void main() {

        while (true) {
            Request request = new Request();
            try {
                setCurrentMenu();
                request.handleRequest(currentMenu , dataCenter);
            }
            catch (InputException e){
                View.getInstance().printError(e);
            }
        }
    }

}
