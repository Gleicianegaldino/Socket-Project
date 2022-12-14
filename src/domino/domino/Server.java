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
	Domino instance = Domino.getInstance();

	private void start() throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Start Server::PORT " + PORT);
		instance.insertPeca();
		clientConnectionLoop();
	}

	/**
	 * @throws IOException
	 */
	private void clientConnectionLoop() throws IOException {
		while (true) {
			PlayerSocket playerSocket = new PlayerSocket(serverSocket.accept());
			playerSocket.firstMessage();
			players.add(playerSocket);
			new Thread(() -> playerMessageLoop(playerSocket)).start();
		}
	}

	private void playerMessageLoop(PlayerSocket playerSocket) {
		Object message;
		try {
			while ((message = playerSocket.getMessage()) != null) {
				// if ("exit".equalsIgnoreCase(message)) {
				// 	return;
				// }
				System.out.println("In::Player " +
						playerSocket.getRemoteSocketAddress() + " " +
						message);

				// sendMessageToAll(playerSocket, message);
			}
		} finally {
			// TODO: handle exception
			playerSocket.close();
		}
	}

	private void sendMessageToAll(PlayerSocket sender, String message) {
		Iterator<PlayerSocket> iterator = players.iterator();
		while (iterator.hasNext()) {
			PlayerSocket playerSocket = iterator.next();
			if (true || !sender.equals(playerSocket)) {
				if (!playerSocket.sendMessage("Out::Player" + sender.getRemoteSocketAddress() + "::" + message)) {
					iterator.remove();
				}
			}
		}
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.start();
		} catch (IOException exception) {
			System.out.println("Error starting server::" + exception.getMessage());
		}
		System.out.println("Finished server");
	}
}
