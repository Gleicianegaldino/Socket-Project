package domino;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class PlayerSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;
    Domino domino = Domino.getInstance();

    public PlayerSocket(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Player " + socket.getRemoteSocketAddress() + " connected");
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

    public void firstMessage(ArrayList<Peca> domino) {
        domino = domino.getRandom();
        out.println(domino);
        out.close();
    }
}