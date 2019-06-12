package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Graphic {
    @FXML
    public ImageView image;
    public Text name;

    public void exit(MouseEvent mouseEvent) {
        UI.getInstance().getPrimaryStage().close();
        System.out.println("you clicked exit");
    }

    public void help(MouseEvent mouseEvent) {
        System.out.println("help");
    }

    public void shopMenuFunc(MouseEvent mouseEvent) throws IOException {
        Parent shopMenu = FXMLLoader.load(view.ShopMenuController.class.getResource("ShopMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Shop menu!");
        primaryStage.setScene(new Scene(shopMenu, 3000, 1000));
        primaryStage.show();
        System.out.println("shop menu function");
    }

    public void collectionMenuFunc(MouseEvent mouseEvent) throws IOException {
        System.out.println("collection menu function");
        Parent CollectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Collection menu");
        primaryStage.setScene(new Scene(CollectionMenu, 3000, 1000));
        primaryStage.show();
    }

    public void battleMenuFunc(MouseEvent mouseEvent) throws IOException {
        System.out.println("battle menu function");
        Parent battleMenu = FXMLLoader.load(view.BattleMenuController.class.getResource("BattleMenuController.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Battle menu");
        primaryStage.setScene(new Scene(battleMenu, 3000, 1000));
        primaryStage.show();
    }
}
