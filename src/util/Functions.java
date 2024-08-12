package util;

import java.util.Random;

import entities.Game;

public class Functions {

	private Random random = new Random();

	private Game game;

	public int cellCounter;

	public void getGame(Game game) {

		this.game = game;

	}

	public void createRealGrid() {

		String[][] realGrid = new String[game.getRows()][game.getColumns()];
		int randomRow, randomColumn;

		// cria a grid sem nada
		for (int i = 0; i < game.getRows(); i++) {
			for (int j = 0; j < game.getColumns(); j++) {

				realGrid[i][j] = "Nada";

			}

		}

		// coloca as bombas em lugares aleatórios
		for (int i = 0; i < game.getBombs(); i++) {

			randomRow = random.nextInt(game.getRows());
			randomColumn = random.nextInt(game.getColumns());

			if (realGrid[randomRow][randomColumn].equals("Bomb")) {
				i--;
			} else {

				realGrid[randomRow][randomColumn] = "Bomb";
			}
		}
		game.setRealGrid(realGrid);
	}

	public void showRealGrid() {

		for (int i = 0; i < game.getRows(); i++) {
			System.out.println();
			for (int j = 0; j < game.getColumns(); j++) {
				System.out.print(game.getCellRealGrid(i, j) + " ");

			}

		}
	}

	public void createGameGrid() {

		String[][] gameGrid = new String[game.getRows()][game.getColumns()];

		for (int i = 0; i < game.getRows(); i++) {
			for (int j = 0; j < game.getColumns(); j++) {
				gameGrid[i][j] = "+";

				game.setGameGrid(gameGrid);
			}
		}
	}

	public void showGameGrid() {

		System.out.println("\nrodada: " + game.getRound());
		System.out.print("\n  ");

		for (int i = 1; i <= game.getColumns(); i++) {
			System.out.print(i + " ");
		}

		for (int i = 0; i < game.getRows(); i++) {

			System.out.println();
			System.out.print((i + 1) + " ");

			for (int j = 0; j < game.getColumns(); j++) {

				System.out.print(game.getCellGameGrid(i, j) + " ");
			}
		}
	}

	public void revealBombs() {
	
		System.out.println("\nrodada: " + game.getRound());
		System.out.print("\n  ");
	
		for (int i = 1; i <= game.getColumns(); i++) {
			System.out.print(i + " ");
		}
	
		for (int i = 0; i < game.getRows(); i++) {
	
			System.out.println();
			System.out.print((i + 1) + " ");
	
			for (int j = 0; j < game.getColumns(); j++) {
	
				if (game.getCellRealGrid(i, j).equals("Bomb")) {
	
					game.setCellGameGrid(i, j, "*");
	
				}
	
				System.out.print(game.getCellGameGrid(i, j) + " ");
			}
		}
	
	}

	public int gameCondition() { // quantidade de celulas restantes
		int count = 0;

		for (int i = 0; i < game.getRows(); i++) {
			for (int j = 0; j < game.getColumns(); j++) {
				if (game.getCellRealGrid(i, j).equals("Nada")) {
					count++;

				}
			}
		}

		if (count != 0) {
			return 1;
		}
		
		return 0;
	}

	public int selectCell(int row, int column) {
		// 1 = coloca, 2 tente de novo, 3 bomba

		if (game.getCellRealGrid(row, column).equals("Nada")) {
			game.roundIncrese();
			return 1;
		}
		if (game.getCellRealGrid(row, column).equals("Algo")) {

			return 2;
		}
		return 3;

	}

	public void setCell(int row, int column) {
		// Mostra o valor que essa célula deve ter
		// (porque é chamada apenas no caso de não ser uma bomba)
		this.cellCounter++;
		if (row < 0 || column < 0 || row > game.getRows() || column > game.getColumns()) {
			return;
		}

		if (game.getCellRealGrid(row, column).equals("Algo")) {
			return;
		}

		Integer counter = 0;

		for (int i = (row - 1); i <= (row + 1); i++) {
			for (int j = (column - 1); j <= (column + 1); j++) {

				if (game.getCellRealGrid(i, j).equals("Bomb")) {
					counter++;
				}
			}
		}

		game.setCellGameGrid(row, column, counter.toString());
		game.setCellRealGrid(row, column, "Algo");

		if (counter == 0) {

			for (int i = (row - 1); i <= (row + 1); i++) {
				for (int j = (column - 1); j <= (column + 1); j++) {

					if (game.getCellRealGrid(i, j).equals("Nada")) {

						setCell(i, j);

					}
				}
			}
		}

	}

}
