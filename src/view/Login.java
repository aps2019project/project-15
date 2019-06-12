package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.menu.AccountMenu;

import java.io.IOException;

public class Login {
    public TextField usernamefield;
    public PasswordField passwordfield;

    public void login(MouseEvent mouseEvent) throws IOException {
        String username = usernamefield.getText();
        String password = passwordfield.getText();
        AccountMenu accountMenu = AccountMenu.getInstance();
        boolean ok = false;
        ok = accountMenu.loginFunction(username, password);
        if(ok) {
            Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
            Stage primaryStage = UI.getInstance().getPrimaryStage();
            primaryStage.setTitle("Duelyst");
            primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
            primaryStage.show();
        }
    }
}
