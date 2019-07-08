import controller.Controller;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import server.Message;
import server.SocketPair;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    private static ArrayList<SocketPair> sockets = new ArrayList<>();

    public static void main(String[] args) {
        try {
            new Thread(() -> launch(args)).start();
            try {
                networking();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            Controller.currentAccount.setLoggedIn(false);
        }
    }

    private static void networking() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

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

    @Override
    public void start(Stage primaryStage) {

        // the music is available only on windows

        try {
            String path = "Taylor-Davis-Pirates-Of-Caribbean-.mp3";
            Media media = new Media(new File(path).toURI().toString());
            System.out.println(media.getSource());
            MediaPlayer mp = new MediaPlayer(media);
            mp.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("SERVER");
        Group group = new Group();
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}