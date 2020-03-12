package uo.mp.minesweeper.game;

import uo.mp.minesweeper.game.actions.Action;
import uo.mp.minesweeper.util.ArgumentsChecks;
import uo.mp.minesweeper.util.StateOfSquare;

public class Square {

	private StateOfSquare state;
	private int squareValue;
	private Action action;
	
	public Square() {
		this.state = StateOfSquare.CLOSED;
		squareValue = 0;
	}

	public int getSquareValue() {
		return squareValue;
	}

	public void setSquareValue(int squareValue) {
		ArgumentsChecks.check(squareValue);
		this.squareValue = squareValue;
	}

	public StateOfSquare getState() {
		return state;
	}

	public void open() {
		this.state = StateOfSquare.OPEN;
	}

	public void unflag() {

		if (this.state != StateOfSquare.OPEN)
			this.state = StateOfSquare.CLOSED;
	}

	public void flag() {

		if (this.state != StateOfSquare.OPEN)
			this.state = StateOfSquare.FLAGGED;
	}

	public void stepOn() {

		if (this.state == StateOfSquare.CLOSED) {
			this.state = StateOfSquare.OPEN;
			action.activate();
		}

	}

	public boolean hasMine() {
		if (squareValue == -1) {
			return true;
		}
		return false;
	}

	public void putMine() {
		this.squareValue = -1;
	}

	public boolean hasFlag() {
		return this.state == StateOfSquare.FLAGGED;
	}

	public boolean isOpen() {
		return this.state == StateOfSquare.OPEN;
	}

	public void setAction(Action action) {
		ArgumentsChecks.check(action);
		this.action = action;
	}
		
}
