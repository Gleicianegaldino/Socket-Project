package domino;

import java.io.*;
import java.net.*;

public class PlayerSocket {
    private final Socket socket;
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    Domino instance = Domino.getInstance();

    public PlayerSocket(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("Player " + socket.getRemoteSocketAddress() + " connected");

        // this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

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

    public Object getMessage() {
        try {
            // return in.readLine();
            return in.readObject();
        } catch (Exception e) {
            System.out.println("exception");
            // TODO: handle exception
            return null;
        }
    }

    public boolean sendMessage(String string) {
        // System.out.println("string" + string);
        // out.println(string);
        // out.flush();
        // return !out.checkError();
        return false;
    }

    public String getFirstMessage() {
        try {
            return in.readLine();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public void firstMessage() throws IOException {
        out.writeObject(instance.getRandom());
        // out.println(instance.getRandom());
        out.flush();
        System.out.println("sent");
    }
}
