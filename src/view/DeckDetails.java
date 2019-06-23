package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DeckDetails {


    public AnchorPane deckShow;
    public VBox cardsAndItems;
    public static String deckName;
    public void initialize() {

    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Parent CollectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Stage primaryStage = UI.getInstance().getPrimaryStage();
        primaryStage.setTitle("Collection menu");
        primaryStage.setScene(new Scene(CollectionMenu, 3000, 1000));
        primaryStage.show();
    }
}
