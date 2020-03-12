package uo.mp.minesweeper.game;

public class GameMove {
	
	private char operation;
	private int row;
	private int col;
	
	public GameMove(char operation, int row, int col) {
		super();
		this.operation = operation;
		this.row = row;
		this.col = col;
	}

	public char getOperation() {
		return operation;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	@Override
	public String toString() {
		return "GameMove [operation=" + operation + ", row=" + row + ", col=" + col + "]";
	}
	
}
