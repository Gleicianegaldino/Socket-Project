package domino;

import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.net.Socket;;

public class Player {
	
	public static void main(String[] args) {
		
		try {
			Socket player = new Socket("127.0.0.1", 6000);
			System.out.println("started player");
			
			ObjectInputStream input = new ObjectInputStream(player.getInputStream());
			Domino peca = (Domino) input.readObject();
			System.out.println("player game: " + peca.toString());

			//player.close();
			//System.out.println("quit");
			
		} catch (Exception e) {
			System.out.println("error");
		}
	}

}
