package domino;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner scanner;
    ArrayList<Peca> game = new ArrayList<Peca>();
    Domino domino = Domino.getInstance();
    int tamanho = 0;

    public void inserPeca()  {
        int d;
        d = scanner.nextInt();
        if (d == 1) {
            game.add(0, domino.getPeca());
            tamanho++;
        } else if (d == 2) {
            game.add(tamanho, domino.getPeca());
            tamanho++;
        }
    }
}