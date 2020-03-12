package uo.mp.minesweeper.game.actions;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.util.ArgumentsChecks;

public class BlowUpAction implements Action {

	private Board board;
	
	public BlowUpAction(Board board) {
		ArgumentsChecks.check(board);
		this.board = board;
	}

	@Override
	public void activate() {
		board.markAsExploded();
		board.unveil();
	}
		
	
	
}
