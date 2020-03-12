package uo.mp.minesweeper.game;

public interface GameInteractor {
	
	GameMove askMove(int rows, int cols);
	void showGame(long elapsedTime, Board board);
	void showGameFinished();
	void showCongratulations(long elapsedTime);
	void showBoom();
	void showReadyToStart();
	
}
