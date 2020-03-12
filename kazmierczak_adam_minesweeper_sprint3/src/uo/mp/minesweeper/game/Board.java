package uo.mp.minesweeper.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.game.actions.ClearAction;
import uo.mp.minesweeper.game.actions.NullAction;
import uo.mp.minesweeper.util.ArgumentsChecks;
import uo.mp.minesweeper.util.Constants;
import uo.mp.minesweeper.util.StateOfTheGame;

public class Board {

	private Square[][] board;
	private StateOfTheGame state;
	private int mines;
	private int flagsLeft;

	public Board(int height, int width, int percentage) {
		
		ArgumentsChecks.check(height, width);
		
		board = new Square[height][width];
		setStateOfTheGame(StateOfTheGame.PLAYING);
		mines = (int) ((height * width) * percentage / 100);
		flagsLeft = mines;
		initBoard();
		armBoard(mines, height, width);
		
		fullFillBoard();
	
	}

	public Board(int mines, Square[][] squares) {

		board = squares;
		setStateOfTheGame(StateOfTheGame.PLAYING);
		this.mines = mines;
		flagsLeft = mines;
		
		fullFillBoard();

	}

	private void armBoard(int mines, int height, int width) {
		for (int i = 0; i < mines; i++) {
			Random randomGen = new Random();
			int randomRow = randomGen.nextInt(height - 1);
			int randomCol = randomGen.nextInt(width - 1);
			board[randomRow][randomCol].putMine();
		}
	}

	private void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = new Square();
			}
		}
	}

	private void fullFillBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].setSquareValue(calculateSquareValue(i, j));
				setBoardActions(i, j);
			}
		}
	}

	private void setBoardActions(int x, int y) {
		
		if(board[x][y].hasMine()) {
			board[x][y].setAction(new BlowUpAction(this));
		}else if(board[x][y].getSquareValue() == 0) {
			board[x][y].setAction(new ClearAction(getNeighbours(x, y)));
		}else {
			board[x][y].setAction(new NullAction());
		}
		
	}
	
	public boolean isExploded(int x, int y) {

		ArgumentsChecks.check(x);
		ArgumentsChecks.check(y);

		if (board[x][y].hasMine() && board[x][y].isOpen()) {
			return true;
		}

		return false;
	}

	public void stepOn(int x, int y) {

		board[x][y].stepOn();

	}

	public void flag(int x, int y) {

		if (!board[x][y].hasFlag() && !board[x][y].isOpen()) {
			
			board[x][y].flag();
			flagsLeft--;

			if (board[x][y].hasMine() && board[x][y].hasFlag()) {
				mines--;
			}

		}

	}

	public void unflag(int x, int y) {

		ArgumentsChecks.check(x);
		ArgumentsChecks.check(y);

		if (board[x][y].hasFlag() && board[x][y].hasMine()) {
			board[x][y].unflag();
			flagsLeft++;
			mines++;
		}else if(board[x][y].hasFlag()) {
			board[x][y].unflag();
			flagsLeft++;
		}
	}

	public void unveil() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j].open();
			}
		}
	}

	public int getFlagsLeft() {
		return this.flagsLeft;
	}

	public int getMinesLeft() {
		return this.mines;
	}

	public void markAsExploded() {
		this.setStateOfTheGame(StateOfTheGame.LOST);
	}

	public char[][] getStatus() {

		char[][] charArray = new char[board.length][board[0].length];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {

				switch (board[i][j].getState()) {
				case CLOSED:
					charArray[i][j] = Constants.unrevealedChar;
					break;
				case FLAGGED:
					charArray[i][j] = Constants.armedChar;
					break;
				case OPEN:
					if (board[i][j].hasMine()) {
						charArray[i][j] = Constants.revealedMineChar;
					} else if (board[i][j].getSquareValue() != 0) {
						charArray[i][j] = (char) (board[i][j].getSquareValue() + '0');
					} else {
						charArray[i][j] = Constants.blankChar;
					}
					break;
				}

			}
		}

		return charArray;

	}

	public Square[][] getSquaresForTest() {		
		return this.board;
	}

	public StateOfTheGame getStateOfTheGame() {
		return state;
	}

	public void setStateOfTheGame(StateOfTheGame state) {
		this.state = state;
	}

	private int calculateSquareValue(int x, int y) {
		
		if(board[x][y].hasMine()) {
			return -1;
		}
		
		int up = y + 1;
		int down = y - 1;
		int left = x - 1;
		int right = x + 1;

		int squareValue = 0;

		for (int i = left; i <= right; i++) {
			for (int j = down; j <= up; j++) {
				if (i >= 0 && j >= 0 && i < board.length 
					&& j < board[0].length && board[i][j].hasMine()) {
					squareValue++;
				}
			}
		}		

		return squareValue;

	}
	
	private List<Square> getNeighbours(int x, int y) {
		
		int up = y + 1;
		int down = y - 1;
		int left = x - 1;
		int right = x + 1;

		List<Square> neighbours = new ArrayList<Square>();
		
		for (int i = left; i <= right; i++) {
			for (int j = down; j <= up; j++) {
				if (i >= 0 && j >= 0 && i < board.length 
					&& j < board[0].length && board[i][j] != board[x][y]) {
					neighbours.add(board[i][j]);
				}
			}
		}		

		return neighbours;

	}
	
	public void uncoverWelcomeArea() {
		
		Random randomGen = new Random();
		int randomRowNotCorner = randomGen.nextInt(rows() - 1) + 1;
		int randomColNotCorner = randomGen.nextInt(cols() - 1) + 1;
		
		do {
			if(board[randomRowNotCorner][randomColNotCorner].getSquareValue() == 0) {
				break;
			}
			randomRowNotCorner = randomGen.nextInt(rows() - 1) + 1;
			randomColNotCorner = randomGen.nextInt(cols() - 1) + 1;
			
		}
		while(board[randomRowNotCorner][randomColNotCorner].getSquareValue() != 0);
				
		board[randomRowNotCorner][randomColNotCorner].stepOn();
		
	}
	
	public int rows() {
		return board.length;
	}
	
	public int cols() {
		return board[0].length;
	}
	
	
}
