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

    DataCenter dataCenter = new DataCenter();
    Menu currentMenu = new AccountMenu();

    public void main() {

        while (true) {
            Request request = new Request();
            try {
                  request.handleRequest(currentMenu , dataCenter);
            }
            catch (InputException e){
                View.getInstance().printError(e);
            }
        }
    }

}
