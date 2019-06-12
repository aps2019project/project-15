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
        System.out.println("you clicked exit");
    }

    public void help(MouseEvent mouseEvent) {
        System.out.println("help");
    }

    public void shopMenufunc(MouseEvent mouseEvent) throws IOException {
        Parent shopMenu = FXMLLoader.load(view.ShopMenuController.class.getResource("ShopMenu.fxml"));

/*        primaryStage.setTitle("Shop menu!");
        primaryStage.setScene(new Scene(shopMenu, 3000, 1000));
        primaryStage.show();*/
        System.out.println("shop menu function");
    }

    public void collectionMenufunc(MouseEvent mouseEvent) {
        System.out.println("collection menu function");
    }

    public void battleMenuFunc(MouseEvent mouseEvent) {
        System.out.println("battle menu function");
    }
}
