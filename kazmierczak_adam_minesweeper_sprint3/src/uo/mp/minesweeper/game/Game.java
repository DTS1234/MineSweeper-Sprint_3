package uo.mp.minesweeper.game;

import uo.mp.minesweeper.util.ArgumentsChecks;
import uo.mp.minesweeper.util.StateOfTheGame;

public class Game {

	private Board gameBoard;
	private long startTime;
	private GameInteractor interactor;

	public Game(Board board) {
		gameBoard = board;
	}

	public void play() {
		
		startTime = System.currentTimeMillis() * 1000;
		gameBoard.uncoverWelcomeArea();
		interactor.showReadyToStart();
		
		while (gameBoard.getStateOfTheGame() == StateOfTheGame.PLAYING) {
			
			interactor.showGame(getElapsedTime(startTime), gameBoard);
			performMove(interactor.askMove(gameBoard.rows(), gameBoard.cols()));
			messageIfEndOfTheGame();
			
			/*
			 * showTime(startTime); showFlagsLeftMessage();
			 * printBoard(gameBoard.getStatus()); showFlagsLeftMessage(); performMove();
			 */
			
		}

	}
	
	private long getElapsedTime(long start) {
		
		return (System.currentTimeMillis()*1000 - start)/1000000;
	}

	private void messageIfEndOfTheGame() {
		if (checkLoose()) {
			
			gameBoard.unveil();
			interactor.showBoom();
			interactor.showGame(getElapsedTime(startTime), gameBoard);
			interactor.showGameFinished();
			
		} else if (checkWin()) {
			
			gameBoard.unveil();
			interactor.showCongratulations(getElapsedTime(startTime));
			interactor.showGameFinished();
			
		}
	}

	private boolean checkLoose() {
		if (gameBoard.getStateOfTheGame() == StateOfTheGame.LOST) {
			return true;
		}
		return false;
	}

	private boolean checkWin() {
		if (gameBoard.getMinesLeft() == 0 && gameBoard.getFlagsLeft() == 0) {
			return true;
		}
		return false;
	}

	private void performMove(GameMove gameMove) {
		
		char move = gameMove.getOperation();
		int row = gameMove.getRow();
		int col = gameMove.getCol();
		
		switch (move) {
		case 's':
			gameBoard.stepOn(row, col);
			break;
		case 'f':

			gameBoard.flag(row, col);
			break;
		case 'u':
			gameBoard.unflag(row, col);
			break;
		}
	}
	
	public void setInteractor(GameInteractor interactor) {
		ArgumentsChecks.isTrue(interactor != null);
		this.interactor = interactor;
	}
	
}
