import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Graphic;
import view.UI;

public class Main extends Application {

    static Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {

        UI.setPrimaryStage(primaryStage);
        Parent mainMenu = FXMLLoader.load(Graphic.class.getResource("Graphic.fxml"));
        Parent accountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        Parent collectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Parent ShopMenu = FXMLLoader.load(view.ShopMenuController.class.getResource("ShopMenu.fxml"));
        Parent battleMap = FXMLLoader.load(view.BattleMap1.class.getResource("BattleMap1.fxml"));
        Parent battleMap2 = FXMLLoader.load(view.BattleMap1.class.getResource("BattleMap2.fxml"));
        Parent battleMenu = FXMLLoader.load(view.BattleMenu.class.getResource("BattleMenu.fxml"));
        Parent deckDetails = FXMLLoader.load(view.DeckDetails.class.getResource("DeckDetails.fxml"));
        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(accountMenu, 3000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Controller.getInstance().initEverything();
        launch(args);
    }
}
