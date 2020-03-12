package uo.mp.minesweeper.game.actions;

import java.util.List;

import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.ArgumentsChecks;

public class ClearAction implements Action{
	
	private List<Square> neighbouringSquares;
	
	public ClearAction(List<Square> neighbouringSquares) {
		
		ArgumentsChecks.check(neighbouringSquares);
		this.neighbouringSquares = neighbouringSquares;
		
	}

	@Override
	public void activate() {
		
		for(Square neighbour : neighbouringSquares) {
			if(!neighbour.hasMine())
				neighbour.stepOn();
		}
		
	}

}
