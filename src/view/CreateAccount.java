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
import model.menu.AccountMenu;

import java.io.IOException;

public class CreateAccount {

    @FXML
    public TextField usernameField;
    @FXML
    public PasswordField passwordField;

    public void createAccount(MouseEvent mouseEvent) throws IOException {

        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean lenght = false;
        if (password.length() < 4) {
            view.View.getInstance().shortPassword();
        } else {
            lenght = true;
        }
        if (lenght) {
            AccountMenu accountMenu = new AccountMenu();
            Controller.currentAccount = accountMenu.register(username, password);
            System.out.println("Account created!");
            Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
            Stage primaryStage = UI.getInstance().getPrimaryStage();
            primaryStage.setTitle("Duelyst");
            primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
            primaryStage.show();
        }
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent accountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Account Menu!");
        primaryStage.setScene(new Scene(accountMenu, 3000, 1000));
        primaryStage.show();
    }

}
