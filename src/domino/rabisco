package domino;

import java.io.Serializable;
import java.util.Random;

public class Domino implements Serializable {
	Peca head;
    Peca tail;
    int lenght;
    
    private static Domino instance = null;
    
    public Domino getInstance() {
    	if (Domino.instance == null) {
    		Domino.instance = new Domino();
    	}
    	return Domino.instance;
    }
    
    Random generator = new Random();
	
	synchronized public void insertPeca() {
    	Peca domino = new Peca();
    	domino.left = generator.nextInt(7);
	    domino.right = generator.nextInt(7);
	    domino.prev = null;
	    domino.next = head;
	    if (head != null) {
	        head.prev = domino;
	    }
	    head = domino;
	    if (lenght == 0) {
	        tail = head;
	    }
	    System.out.println("|" + domino.left + "|" + domino.right + "|");
        lenght++;
    }
}