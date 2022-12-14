package domino;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * @author rayane
 *
 */
public class Domino {
	
	ArrayList<Peca> domino = new ArrayList();
	ArrayList<Peca> all = new ArrayList();
	ArrayList<Peca> hand = new ArrayList();
	static Domino instance = null;

	public static Domino getInstance() {
		if ((instance != null)) {
			return instance;
		}
		instance = new Domino();
		return instance;
	}
	
	/**
	 * @return void
	 * 
	 */
	public void insertPeca() {
		
		for (int i = 0; i < 7; i++) {
			for (int j = i; j < 7; j++) {
				Peca peca = new Peca();
				peca.left = i;
				peca.right = j;
				domino.add(peca);
			}
		}
		System.out.println(domino);
	}
	
	/**
	 *@return Peca
	 *
	 */
	public ArrayList<Peca> getRandom() {
		
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			int index = random.nextInt(domino.size());
			hand.add(domino.get(index));
			domino.remove(index); //no repetitions
		}
		all.addAll(hand);
		System.out.println(hand);
		return all;
	}
	
	public Peca getPeca(int index) {
		System.out.println("Im hereeee");
		System.out.println(hand);
		Peca get = hand.get(index);
		System.out.println(hand.get(index));
		hand.remove(index);
		System.out.println(hand);
		return get;
	}
	
	public void girarPeca() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Select:");
		int index = scanner.nextInt();
		hand.get(index);
		System.out.println(hand.get(index));
		int l = hand.get(index).left;
		int r = hand.get(index).right;
		hand.get(index).left = r;
		hand.get(index).right = l;
		System.out.println(hand.get(index));
	}
	
}