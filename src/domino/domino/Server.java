package domino;

import java.io.*;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Server {
	public static final int PORT = 4000;
	private ServerSocket serverSocket;
	// private PlayerSocket playerSocket;
	private final List<PlayerSocket> players = new LinkedList<>();
	Domino domino = Domino.getInstance();

	private void start() throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Start Server::PORT " + PORT);
		domino.insertPeca();
		clientConnectionLoop();
	}

	/**
	 * @throws IOException
	 */
	private void clientConnectionLoop() throws IOException {
		while (true) {
			PlayerSocket playerSocket = new PlayerSocket(serverSocket.accept());
			players.add(playerSocket);
			new Thread(() -> playerMessageLoop(playerSocket)).start();
			//domino.getRandom();
		}
	}

	private void playerMessageLoop(PlayerSocket playerSocket) {
		String msg;
		try {
			while ((msg = playerSocket.getMessage()) != null) {
				if ("exit".equalsIgnoreCase(msg)) {
					return;
				}
				System.out.println("In::Player " +
						playerSocket.getRemoteSocketAddress() + " " +
						msg);

				sendMessageToAll(playerSocket, msg);
			}
		} finally {
			// TODO: handle exception
			playerSocket.close();
		}
	}

	private void sendMessageToAll(PlayerSocket sender, String msg) {
		Iterator<PlayerSocket> iterator = players.iterator();
		while (iterator.hasNext()) {
			PlayerSocket playerSocket = iterator.next();
			if (!sender.equals(playerSocket)) {
				if (!playerSocket.sendMessage("Out::Player" + sender.getRemoteSocketAddress() + "::" + msg)) {
					iterator.remove();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.start();
		} catch (IOException ex) {
			System.out.println("Error starting server::" + ex.getMessage());
		}
		System.out.println("Finished server");
	}
}
