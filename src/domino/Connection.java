package domino;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection extends Thread {
	Socket player;
	
	Connection(Socket player) {
		this.player = player;
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("player connected with thread (" + this.getId() + "): " + player.getInetAddress());
		    
			for (int i = 0; i < 6; i++) {
				Domino domino = new Domino();
				ObjectOutputStream out = new ObjectOutputStream(player.getOutputStream());
				System.out.println("distributing: " + domino.toString());
				out.writeObject(domino);
			}
			
			//player.close();
		} catch (IOException e) { // | ClassNotFoundException
			e.printStackTrace();
		}
	}

	private Object Peca() {
		// TODO Auto-generated method stub
		return null;
	}
}