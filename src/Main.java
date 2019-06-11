import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Graphic;

public class Main extends Application {

    static Controller controller = new Controller();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent mainMenu = FXMLLoader.load(Graphic.class.getResource("Graphic.fxml"));
        //Parent root = FXMLLoader.load(AccountMenu.class.getResource("AccountMenu.fxml"));
        Parent root = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));

        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
