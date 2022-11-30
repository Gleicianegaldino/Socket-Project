package domino;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Server {
	public static final int PORT = 4000;
	private ServerSocket serverSocket;
	private final List<PlayerSocket> players = new LinkedList<>();
	
	private void start() throws IOException {
		serverSocket = new ServerSocket(PORT);
		System.out.println("Servidor iniciado na porta " + PORT);
		clientConnectionLoop();
	}
	
	private void clientConnectionLoop() throws IOException{
		while(true) {
			PlayerSocket playerSocket = new PlayerSocket(serverSocket.accept());
			players.add(playerSocket);
			new Thread(() ->  playerMessageLoop(playerSocket)).start();
		}
	}

	private void playerMessageLoop(PlayerSocket playerSocket) {
		String msg;
		try {
			while ((msg = playerSocket.getMessage()) != null) {
				if ("sair".equalsIgnoreCase(msg)) {
					return;				
				}
				System.out.println("Msg recebida do cliente" +
				playerSocket.getRemoteSocketAddress() +
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
		while(iterator.hasNext()) {
			PlayerSocket playerSocket = iterator.next();
			if (!sender.equals(playerSocket)) {
				if (!playerSocket.sendMessage("player " + sender.getRemoteSocketAddress() + ":" + msg)) {
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
			System.out.println("Erro ao iniciar o servidor: " + ex.getMessage());
		}
		System.out.println("Servidor finalizado");
	}
}
