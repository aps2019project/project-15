package client;

import client.Message;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 8989);
            OutputStream outputStream = socket.getOutputStream();
            Formatter formatter = new Formatter(outputStream);

            System.out.println("enter your name:");
            String clientName = scanner.nextLine();

            new Thread(() -> {
                String message = scanner.nextLine();
                while (!message.equals("poweroff") && socket.isConnected()) {
                    formatter.format(new Message(clientName, message).toJson() + "\n");
                    formatter.flush();
                    message = scanner.nextLine();
                }
                try {
                    socket.close();
                } catch (IOException ignored) {
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
}
