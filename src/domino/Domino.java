package domino;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author rayane
 *
 */
public class Domino {
	
	int domino[] = null;
	
	/**
	 * init -> void
	 * @return void
	 * 
	 */
	public void insertPeca() {
		
		Peca peca = new Peca();
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {
				peca.left = i;
				peca.right = j;
				System.out.println("|" + peca.left + "|" + peca.right + "|");
//				ArrayList<Peca>
//				System.out.println(peca);
//				inserir no os dados no array domino
			}
		}
	}
	
	/**
	 *getRandom -> Peca
	 *@return Peca
	 *
	 */
//	public Peca getPeca() {
//		Random random = new Random();
//		Peca get = domino.get(random.nextInt());
//	}
}