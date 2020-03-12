package board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.util.Constants;

public class UncoverWelcomeAreaTests {

	private Board testBoard;
	private Square[][] testSquares;

	@Before
	public void setUp() throws Exception {

		testSquares = new Square[5][5];
		
		for (int i = 0; i < testSquares.length; i++) {
			for (int j = 0; j < testSquares.length; j++) {
				testSquares[i][j] = new Square();
			}
		}
		
		testSquares[0][0].putMine();
		testSquares[0][1].putMine();
		testSquares[0][2].putMine();
		testSquares[0][3].putMine();
		testSquares[0][4].putMine();
		testSquares[1][3].putMine();
		testSquares[1][4].putMine();
		testSquares[2][3].putMine();
		testSquares[2][4].putMine();
		testSquares[3][3].putMine();
		testSquares[3][4].putMine();
		testSquares[4][0].putMine();

		
		testBoard = new Board(0, testSquares);

	}
	
	/*
	 * GIVEN: board with only one possibility of uncovering
	 * WHEN: calling a uncoverWelcome method 
	 * THEN: the only possible squares are revealed
	 */
	@Test
	public void unCoverWelcomeTest() {
		
		testBoard.uncoverWelcomeArea();
		char[][] arrayOfChars = testBoard.getStatus();
	
		assertEquals(Constants.blankChar, arrayOfChars[2][0]);
		assertEquals(Constants.blankChar, arrayOfChars[2][1]);
		assertEquals(Constants.twochar, arrayOfChars[1][0]);
		assertEquals(Constants.threechar, arrayOfChars[1][1]);
		assertEquals(Constants.fivechar, arrayOfChars[1][2]);
		assertEquals(Constants.threechar, arrayOfChars[2][2]);
		assertEquals(Constants.twochar, arrayOfChars[3][2]);
		assertEquals(Constants.onechar, arrayOfChars[3][0]);
		assertEquals(Constants.onechar, arrayOfChars[3][1]);		
		
	}

}
