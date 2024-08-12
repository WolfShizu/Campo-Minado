package application;

import java.util.Scanner;

import entities.Game;
import util.Functions;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Functions fun = new Functions();
		Game game = new Game();

		fun.getGame(game);

		int rows, columns, bombs;

		// definindo os valores do jogo

		System.out.print("Digite o numero de linhas: ");
		rows = sc.nextInt();

		System.out.print("Digite o numero de colunas: ");
		columns = sc.nextInt();

		System.out.print("Digite o numero de bombas: ");
		bombs = sc.nextInt();

		game.setRules(rows, columns, bombs);

		fun.createRealGrid();
		fun.createGameGrid();
		// fun.showRealGrid(); // debug
		// inicio do jogo

		int selectedRow, selectedColumn;
		boolean play = true;

		System.out.println("\n\nComeço do jogo" + "\n---------------------------------"
				+ "\n Selecione a linha e coluna dessa forma: 0 0\n\n");

		String text = "\nSelecione a linha e a coluna";

		while (play) {
			if (fun.gameCondition() == 0) {
				fun.revealBombs();
				System.out.println("\nVocê ganhou!!");
				play = false;
				break;
			}

			fun.showGameGrid();
			System.out.println("\nVerificações: " + fun.cellCounter); // debug
			System.out.println(text);
			selectedRow = sc.nextInt() - 1;
			selectedColumn = sc.nextInt() - 1;

			switch (fun.selectCell(selectedRow, selectedColumn)) {

			case 1:
				fun.setCell(selectedRow, selectedColumn);
				text = "\nSelecione a linha e a coluna";
				break;

			case 2:
				text = "\nLugar já descoberto, tente novamente";
				break;

			case 3:
				System.out.println("você perdeu");
				fun.revealBombs();
				play = false;
				break;
			}

		}

		sc.close();
	}
}