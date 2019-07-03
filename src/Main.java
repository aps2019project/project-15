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
import server.Message;
import server.SocketPair;
import view.UI;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private static ArrayList<SocketPair> sockets = new ArrayList<>();
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
            /*try {
                networking();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            new Thread(() -> {
                loadAccounts();
                Controller.getInstance().initEverything();
                launch(args);
            }).start();
        } catch (RuntimeException ex) {
            ex.printStackTrace();
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

    private static void networking() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8989);

        new Thread() {
            Scanner scanner = new Scanner(System.in);

            @Override
            public void run() {
                if (scanner.nextLine().equals("poweroff")) {
                    System.exit(0);
                }
            }
        }.start();

        while (true) {
            try {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    System.out.println("socket added");
                    try {
                        sockets.add(new SocketPair(socket));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scanner scanner = null;
                    try {
                        scanner = new Scanner(socket.getInputStream());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    while (socket.isConnected()) {
                        if (scanner != null && scanner.hasNextLine()) {
                            String message = scanner.nextLine();
                            for (SocketPair other : sockets) {
                                if (other.getSocket().equals(socket)) continue;
                                synchronized (other) {
                                    other.getFormatter().format(new Message("server", message).toJson() + "\n");
                                    other.getFormatter().flush();
                                }
                            }
                            System.out.println(message);
                        }
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
