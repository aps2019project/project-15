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
        Parent AccountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        Parent CollectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Parent ShopMenu = FXMLLoader.load(view.ShopMenuController.class.getResource("ShopMenu.fxml"));
        Parent battleMap = FXMLLoader.load(view.BattleMap1.class.getResource("BattleMap1.fxml"));
        Parent battleMenu = FXMLLoader.load(view.BattleMenuController.class.getResource("BattleMenuController.fxml"));
        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(mainMenu, 3000, 1000));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
