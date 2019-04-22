package controller;

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
    private Menu currentMenu = new AccountMenu();
    public static Account currentAccount = new Account();

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
