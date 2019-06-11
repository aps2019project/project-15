package view;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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

    public void shopMenufunc(MouseEvent mouseEvent) {
        System.out.println("shop menu function");
    }

    public void collectionMenufunc(MouseEvent mouseEvent) {
        System.out.println("collection menu function");
    }

    public void battleMenuFunc(MouseEvent mouseEvent) {
        System.out.println("battle menu function");
    }
}
