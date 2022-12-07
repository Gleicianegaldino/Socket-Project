package domino;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable {
	private static final String ServerAddress = "127.0.0.1";
	private PlayerSocket playerSocket;
	private Scanner scanner;
	Domino domino = Domino.getInstance();

	public Player() {
		scanner = new Scanner(System.in);
	}

	public void start() throws IOException {
		try {
			playerSocket = new PlayerSocket(new Socket(ServerAddress, Server.PORT));
			System.out.println("Player A connected " + ServerAddress + "::" + Server.PORT);
			new Thread(this).start();
			messageLoop();
		} finally {
			// TODO: handle exception
			playerSocket.close();
		}
	}

	@Override
	public void run() {
		String d;
		while ((d = playerSocket.getMessage()) != null) {
			System.out.println("In::Server" + playerSocket.getMessage());
		}
	}

	private void messageLoop() throws IOException {
		String d;
		do {
			System.out.println("Digite uma mensagem (caso queira finalizar, digite 'exit')");
			d = scanner.nextLine();
			playerSocket.sendMessage(d);
		} while (!d.equalsIgnoreCase("exit"));
	}

	public static void main(String[] args) {
		try {
			Player player = new Player();
			player.start();
		} catch (IOException ex) {
			System.out.println("Error starting player::" + ex.getMessage());
		}
		System.out.println("Finished player");
	}
}
