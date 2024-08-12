package entities;

public class Game {

	private String[][] realGrid;
	private String[][] gameGrid;
	private int rows, columns, bombs, round;

	public void setRules(int rows, int columns, int bombs) {

		this.rows = rows;
		this.columns = columns;
		this.bombs = bombs;

	}

	public int getRows() {

		return this.rows;

	}

	public int getColumns() {

		return this.columns;

	}

	public int getBombs() {
	
		return this.bombs;
	
	}

	public int getRound() {
		return this.round;
	}

	public void roundIncrese() {

		this.round++;

	}

	public void setRealGrid(String[][] realGrid) {

		this.realGrid = realGrid;

	}

	public String getCellRealGrid(int row, int column) {

		if (row >= 0 && column >= 0 && row < this.rows && column < this.columns) {
			return realGrid[row][column];
		}
		return "";

	}

	public void setCellRealGrid(int row, int column, String n) {

		this.realGrid[row][column] = n;

	}

	public void setGameGrid(String[][] gameGrid) {

		this.gameGrid = gameGrid;

	}

	public String getCellGameGrid(int rows, int columns) {

		return gameGrid[rows][columns];

	}

	public void setCellGameGrid(int row, int column, String n) {

		this.gameGrid[row][column] = n;

	}

}
