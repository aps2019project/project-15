package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Account;
import model.menu.AccountMenu;

import java.util.Objects;

public class CreateAccount {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;


    public void createAccount(MouseEvent mouseEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Account account = new Account();
        AccountMenu accountMenu = new AccountMenu();
        Controller.currentAccount = accountMenu.register(username , password);
        System.out.println("Account created!");
    }
}
