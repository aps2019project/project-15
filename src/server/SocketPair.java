package server;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

public class SocketPair {
    private Socket socket;
    private Formatter formatter;

    public SocketPair(Socket socket) throws IOException {
        this.socket = socket;
        this.formatter = new Formatter(socket.getOutputStream());
    }

    public Socket getSocket() {
        return socket;
    }

    public Formatter getFormatter() {
        return formatter;
    }
}
