import com.gilecode.yagson.YaGson;
import com.gilecode.yagson.YaGsonBuilder;
import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Account;
import model.DataCenter;
import view.Graphic;
import view.UI;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;

public class Main extends Application {

    static Controller controller = new Controller();
    private int counter = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        UI.setPrimaryStage(primaryStage);
        Parent mainMenu = FXMLLoader.load(Graphic.class.getResource("Graphic.fxml"));
        Parent accountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        Parent collectionMenu = FXMLLoader.load(view.CollectionMenu.class.getResource("CollectionMenu.fxml"));
        Parent ShopMenu = FXMLLoader.load(view.ShopMenuController.class.getResource("ShopMenu.fxml"));
        Parent battleMap = FXMLLoader.load(view.BattleMap1.class.getResource("BattleMap1.fxml"));
        Parent battleMenu = FXMLLoader.load(view.BattleMenu.class.getResource("BattleMenu.fxml"));
        Parent deckDetails = FXMLLoader.load(view.DeckDetails.class.getResource("DeckDetails.fxml"));
        Parent singlePlayer = FXMLLoader.load(view.singlePlayerBattleOptions.class.getResource("singlePlayerBattleOptions.fxml"));
        Parent multiPlayer = FXMLLoader.load(view.MultiPlayer.class.getResource("MultiPlayer.fxml"));
        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(accountMenu, 3000, 1000));

        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            loadAccounts();
            Controller.getInstance().initEverything();
            launch(args);
        }
        finally {
            Controller.currentAccount.setLoggedIn(false);
            Account.saveAccounts();
        }

    }

    private static void loadAccounts() {
        try {
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = yaGson.fromJson(reader, (Type) Account[].class);
            if (accounts != null) {
                for (Account account : accounts) {
                    DataCenter.getInstance().putAccount(account);
                    System.out.println(account.getUsername());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
