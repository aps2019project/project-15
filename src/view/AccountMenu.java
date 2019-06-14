package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountMenu {

    public void createAccount(MouseEvent mouseEvent) throws IOException {
        Parent createAccount = FXMLLoader.load(view.CreateAccount.class.getResource("CreateAccount.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("New Account!");
        primaryStage.setScene(new Scene(createAccount, 3000, 1000));
        primaryStage.show();
    }

    public void exit(MouseEvent mouseEvent) {
        UI.getInstance().getPrimaryStage().close();
    }

    public void login(MouseEvent mouseEvent) throws IOException {
        Parent login = FXMLLoader.load(view.Login.class.getResource("Login.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Login!");
        primaryStage.setScene(new Scene(login, 3000, 1000));
        primaryStage.show();
    }

    public void showLeaderBoard(MouseEvent mouseEvent) throws IOException {
        Parent leaderBoard = FXMLLoader.load(view.leaderboard.class.getResource("leaderboard.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("LeaderBoard");
        primaryStage.setScene(new Scene(leaderBoard, 3000, 1000));
        primaryStage.show();
    }
}