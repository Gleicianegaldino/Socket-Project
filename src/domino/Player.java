package domino;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable {
	private static final String ServerAddress = "127.0.0.1";
	private PlayerSocket playerSocket;
	private Scanner scanner;
	
	public Player() {
		scanner = new Scanner(System.in);
	}
	
	public void start() throws IOException {
		try {
			playerSocket = new PlayerSocket(new Socket(ServerAddress, Server.PORT));
			System.out.println("Cliente conectado ao servidor em " + ServerAddress + " : " + Server.PORT);
			new Thread(this).start();
			messageLoop();
		} finally {
			// TODO: handle exception
			playerSocket.close();
		}
	}

	@Override
	public void run() {
		String msg;
		while ((msg = playerSocket.getMessage()) != null) {
			System.out.println("Msg recebida do server: " + playerSocket.getMessage());	
		}
	}
	
	private void messageLoop() throws IOException {
		String msg;
		do {
			System.out.println("Digite uma mensagem (caso queira finalizar, digite 'sair')");
			msg = scanner.nextLine();
			playerSocket.sendMessage(msg);
		} while(!msg.equalsIgnoreCase("sair"));
	}
	
	public static void main(String[] args) {
		try {
			Player client = new Player();
			client.start();
		} catch (IOException ex) {
			System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
		}
		System.out.println("Cliente finalizado");
	}
}
