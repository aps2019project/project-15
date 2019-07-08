package client;

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

import java.io.*;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Main extends Application {
    private static Scanner scanner = new Scanner(System.in);

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
            File file = new File(".config");
            String ip = "localhost";
            int port = 8000;

            if (file.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                ip = bufferedReader.readLine();
                port = Integer.parseInt(bufferedReader.readLine());
            }

            Socket socket = new Socket(ip, port);

            OutputStream outputStream = socket.getOutputStream();
            Formatter formatter = new Formatter(outputStream);

            System.out.println("enter your name:");
            String clientName = scanner.nextLine();

            new Thread(() -> {
                loadAccounts();
                Controller.getInstance().initEverything();
                launch(args);
                Account.saveAccounts();
            }).start();

            new Thread(() -> {
                String message = scanner.nextLine();
                while (!message.equals("poweroff") && socket.isConnected()) {
                    formatter.format(new Message(clientName, message).toJson() + "\n");
                    formatter.flush();
                    message = scanner.nextLine();
                }
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                try {
                    Scanner scanner = new Scanner(socket.getInputStream());

                    while (true) {
                        if (scanner.hasNextLine()) {
                            System.out.print(Message.fromJson(scanner.nextLine()));
                        }
                    }
                } catch (IOException ignored) {
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
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
