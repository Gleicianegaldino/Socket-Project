package domino;

import java.net.ServerSocket;

public class Server {
	
	public static void main(String[] args) {
	
		try {
			ServerSocket server = new ServerSocket(6000);
			System.out.println("start server");
			
			while(true) {
				Middle game = new Middle(server.accept());
				game.start();
			}
		} catch (Exception e) {
				System.out.println("error");
		}
	}
}
