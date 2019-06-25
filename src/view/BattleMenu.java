package view;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.AI;

import java.io.IOException;

public class BattleMenu {
    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.Graphic.class.getResource("Graphic.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Duelyst");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void singlePlayer(MouseEvent mouseEvent) throws IOException {
        Controller.enemyAccount = AI.getInstance();
        Parent mainMenu = FXMLLoader.load(view.singlePlayerBattleOptions.class.getResource("singlePlayerBattleOptions.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Game Mode");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public void multiPlayer(MouseEvent mouseEvent) throws IOException {
        Parent mainMenu = FXMLLoader.load(view.MultiPlayer.class.getResource("MultiPlayer.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Game Mode");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }
}
