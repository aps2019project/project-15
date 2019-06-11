import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.AccountMenu;
import view.Graphic;
import view.PersonOverview;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainMenu = FXMLLoader.load(Graphic.class.getResource("Graphic.fxml"));
        Parent accountMenu = FXMLLoader.load(AccountMenu.class.getResource("AccountMenu.fxml"));



        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(accountMenu, 1000, 700));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
