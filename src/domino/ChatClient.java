package Principal;

import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String ServerAddress = "127.0.0.1";
	private Socket clientSocket;
	private Scanner scanner;
	private PrintWriter out;
	
	public ChatClient() {
		scanner = new Scanner(System.in);
	}
	
	public void start() throws IOException {
		clientSocket = new Socket(ServerAddress, ChatServer.PORT);
		this.out = new PrintWriter(clientSocket.getOutputStream());
		System.out.println("Cliente conectado ao servidor em " + ServerAddress + " : " + ChatServer.PORT);
		messageLoop();
	}
	
	private void messageLoop() throws IOException {
		String msg;
		do {
			System.out.println("Digite uma mensagem (caso queira finalizar, digite 'sair')");
			msg = scanner.nextLine();
			out.println(msg);
		} while(!msg.equalsIgnoreCase("sair"));
	}
	
	public static void main(String[] args) {
		try {
			ChatClient client = new ChatClient();
			client.start();
		} catch (IOException ex) {
			System.out.println("Erro ao iniciar o cliente: " + ex.getMessage());
		}
		System.out.println("Cliente finalizado");
	}
}
