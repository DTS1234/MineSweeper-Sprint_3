package action.clear;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.Square;
import uo.mp.minesweeper.game.actions.ClearAction;

public class ClearActionActivateTests {

	private Square[][] testSquares;
	private Board testBoard;
	private List<Square> neighboursTests;
	private ClearAction clearAction;
	
	@Before
	public void setUp() throws Exception {

		testSquares = new Square[9][9];
		
		for (int i = 0; i < testSquares.length; i++) {
			for (int j = 0; j < testSquares.length; j++) {
				testSquares[i][j] = new Square();
			}
		}
		
		testSquares[1][1].putMine();
		testSquares[1][2].putMine();
		
		testBoard = new Board(0, testSquares);
		testBoard.stepOn(0, 0);

		testBoard.flag(1, 1);
		testBoard.flag(2, 2);
		
		int up = 5 + 1;
		int down = 5 - 1;
		int left = 5 - 1;
		int right = 5 + 1;

		neighboursTests = new ArrayList<Square>();
		
		for (int i = left; i <= right; i++) {
			for (int j = down; j <= up; j++) {
				if (i >= 0 && j >= 0 && i < testSquares.length 
					&& j < testSquares.length && testSquares[i][j] != testSquares[5][5]) {
					neighboursTests.add(testSquares[i][j]);
				}
			}
		}		

		clearAction = new ClearAction(neighboursTests);
		
	}
	
	
	/*
	 * GIVEN: clear action with neighbours of 5, 5 square 
	 * WHEN: calling a activate method 
	 * THEN: step on invoked on every neighbour
	 */
	@Test
	public void activateTest() {
		
		clearAction.activate();
		
		for(int i = 0; i<testBoard.getSquaresForTest().length; i++) {
			for(int j = 0; j<testBoard.getSquaresForTest().length; j++) {
				
				if(i<=2 && j<=3) {
					assertTrue(testBoard.getSquaresForTest()[i][j].getSquareValue() != 0);
					}else {
					assertTrue(testBoard.getSquaresForTest()[i][j].getSquareValue() == 0 
							&& testBoard.getSquaresForTest()[i][j].isOpen());
				}
				
			}
			
			assertTrue(testBoard.getSquaresForTest()[1][1].isOpen() == false);
			assertTrue(testBoard.getSquaresForTest()[1][2].isOpen() == false);
			assertTrue(testBoard.getSquaresForTest()[2][2].isOpen() == false);
			
		}
		
		
	}

}
