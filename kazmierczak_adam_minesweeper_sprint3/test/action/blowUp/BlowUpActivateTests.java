package action.blowUp;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.actions.BlowUpAction;
import uo.mp.minesweeper.util.StateOfTheGame;

public class BlowUpActivateTests {

	private Square[][] testSquares;
	private Board testBoard;
	private BlowUpAction blowUpAction;
	
	@Before
	public void setUp() throws Exception {

		testSquares = new Square[9][9];
		
		for (int i = 0; i < testSquares.length; i++) {
			for (int j = 0; j < testSquares.length; j++) {
				testSquares[i][j] = new Square();
			}
		}
		
		testBoard = new Board(0, testSquares);
		blowUpAction = new BlowUpAction(testBoard);
		
	}
	
	@Test
	public void activateTests() {
		
		blowUpAction.activate();
		assertTrue(testBoard.getStateOfTheGame() == StateOfTheGame.LOST);
		
	}

}
