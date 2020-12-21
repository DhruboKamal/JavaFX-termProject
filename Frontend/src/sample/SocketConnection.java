package sample;

import java.io.IOException;
import java.net.Socket;

public class SocketConnection {

    private static SocketConnection connector = null;
    private Socket socket;

    private SocketConnection() {
        try {
            this.socket = new Socket("localhost", 55555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SocketConnection getInstance()
    {
        if(connector == null)
            connector = new SocketConnection();

        return connector;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}