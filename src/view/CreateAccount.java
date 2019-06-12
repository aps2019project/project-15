package view;

import controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Account;
import model.menu.AccountMenu;

import java.io.IOException;
import java.util.Objects;

public class CreateAccount {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;


    public void createAccount(MouseEvent mouseEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        Account account = new Account();
        AccountMenu accountMenu = new AccountMenu();
        Controller.currentAccount = accountMenu.register(username , password);
        System.out.println("Account created!");
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }
}
