package domino;

import java.io.*;
import java.net.*;

public class PlayerSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public PlayerSocket(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Cliente " + socket.getRemoteSocketAddress() + " conectado");
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
    }

    public SocketAddress getRemoteSocketAddress() {
        return socket.getRemoteSocketAddress();
    }

    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error");
        }
    }

    public String getMessage() {
        try {
            return in.readLine();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public boolean sendMessage(String msg) {
        out.println(msg);
        return !out.checkError();
    }
}
