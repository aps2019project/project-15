
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
import view.UI;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;

public class Main extends Application {

    static Controller controller = new Controller();
    private int counter = 1;

    @Override
    public void start(Stage primaryStage) throws Exception {

        UI.setPrimaryStage(primaryStage);
        Parent accountMenu = FXMLLoader.load(view.AccountMenu.class.getResource("AccountMenu.fxml"));
        primaryStage.setTitle("DUELYST");
        primaryStage.setScene(new Scene(accountMenu, 3000, 1000));

        primaryStage.show();
    }

    public static void main(String[] args) {
        try {
            loadAccounts();
            Controller.getInstance().initEverything();
            launch(args);
        } finally {

            Controller.currentAccount.setLoggedIn(false);
        }

    }

    private static void loadAccounts() {
        try {
            YaGson yaGson = new YaGsonBuilder().setPrettyPrinting().create();
            Reader reader = new FileReader("accounts.json");
            Account[] accounts = yaGson.fromJson(reader, (Type) Account[].class);
            if (accounts != null) {
                for (Account account : accounts) {
                    DataCenter.getInstance().getAccounts().put(account.getUsername(), account);
                    System.out.println(account.getUsername());
                }
            }
            for (Account account : DataCenter.getInstance().getAccounts().values()) {
                account.setLoggedIn(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
