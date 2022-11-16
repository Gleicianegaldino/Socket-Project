package domino;

public class Tests {
	
	public static void main(String[] args) {
		Domino first = new Domino();
		first.insertPeca();
		for (int i = 0; i < 27; i++) {
			Domino domino = new Domino();
			domino.insertPeca();
			if (first.head.left == first.head.left && first.head.right == domino.head.right) {
				System.out.println("repetiu");
			}
		}
	}
}
