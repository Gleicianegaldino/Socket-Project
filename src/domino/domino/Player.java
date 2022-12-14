package domino;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Runnable {
	private static final String ServerAddress = "127.0.0.1";
	private PlayerSocket playerSocket;
	private Scanner scanner;
	Domino instance = Domino.getInstance();
	Peca peca;

	public Player() {
		scanner = new Scanner(System.in);
	}

	public void start() throws IOException {
		try {
			playerSocket = new PlayerSocket(new Socket(ServerAddress, Server.PORT));
			System.out.println("Player A connected " + ServerAddress + "::" + Server.PORT);
			new Thread(this).start();
			messageLoop();
			playerSocket.getFirstMessage();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// TODO: handle exception
			playerSocket.close();
		}
	}

	@Override
	public void run() {
		Object d;
		while ((d = playerSocket.getMessage()) != null) {
			//  ((ArrayList<Peca>)d).get(1);
			System.out.println(((ArrayList)d).get(1));
			System.out.println("In::Server " + d);
		}
	}

	private void messageLoop() throws IOException {
		// String message;
		int message;
		do {
			System.out.println("Escolha uma peça por indice: (caso queira finalizar, digite 'exit')");
			// message = scanner.nextLine();
			message = scanner.nextInt();
			System.out.println("message" + message);
			System.out.println(instance.getPeca(message));
			playerSocket.sendMessage(instance.getPeca(message).toString());
		} while (message >= 0 && message <= 5);
		// !message.equalsIgnoreCase("exit")
		// message >= 0 && message <= 5
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
