package uo.mp.minesweeper.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uo.mp.minesweeper.game.Board;
import uo.mp.minesweeper.game.GameInteractor;
import uo.mp.minesweeper.game.GameMove;
import uo.mp.minesweeper.util.ArgumentsChecks;

public class ConsoleGameInteractor implements GameInteractor {

	@Override
	public GameMove askMove(int rows, int cols) {

		String wrongCordinatesMsg = "Passed cordinate doesn't exist !";
		String wrongMoveMsg = "You have to enter s, f, or u !";

		int row = firstCoordinateSelection();
		ArgumentsChecks.isTrue(row <= rows && row >= 0, wrongCordinatesMsg);

		int col = secondCoordinateSelection();
		ArgumentsChecks.isTrue(col <= cols && col >= 0, wrongCordinatesMsg);

		char operation = moveSelection();
		ArgumentsChecks.checkMove(operation, wrongMoveMsg);

		return new GameMove(operation, row, col);
	}

	private int firstCoordinateSelection() {
		System.out.println("Pick first cordinate :");
		char rowChar = readChar();
		Integer row = Integer.parseInt(rowChar + "");
		return row;

	}

	private int secondCoordinateSelection() {
		System.out.println("Pick second cordinate :");
		char colChar = readChar();
		Integer col = Integer.parseInt(colChar + "");
		return col;
	}

	private char moveSelection() {

		System.out.println("movement (s|f|u) :");
		char move = readChar();

		return move;
	}

	@Override
	public void showGame(long elapsedTime, Board board) {

		showTime(elapsedTime);
		showFlagsLeftMessage(board);
		showBoard(board);

	}

	private void showFlagsLeftMessage(Board board) {
		System.out.println("Flags left: " + board.getFlagsLeft());
	}

	private void showTime(long elapsedTime) {
		System.out.println("Time : " + elapsedTime + " seconds");
	}

	private void showBoard(Board board) {

		int cols = board.cols();
		int rows = board.rows();
		
		printLabelRow(cols);
		System.out.println();
		
		for (int i = 0; i < rows; i++) {
			printBreak(cols);System.out.println();
			printBoardRow(board, i);
		}
		
		printBreak(cols);
		
		System.out.println();

	}

	private void printLabelRow(int cols) {

		for (int i = 0; i < cols; i++) {
			System.out.print("  " + i + " ");
		}

	}

	private void printBreak(int cols) {

		System.out.print(" +");

		for (int i = 0; i < cols; i++) {
			System.out.print("---+");
		}

	}

	private void printBoardRow(Board board, int row) {

		char[][] boardChars = board.getStatus();

		StringBuilder boardRow = new StringBuilder();
		boardRow.append(row);

		for (int i = 0; i < boardChars.length; i++) {
			boardRow.append("| " + boardChars[row][i] + " ");
		}
		boardRow.append("|");

		System.out.println(boardRow.toString());

	}

	@Override
	public void showGameFinished() {
		
		System.out.println("GAME OVER");
		
	}

	@Override
	public void showCongratulations(long elapsedTime) {
		
		System.out.println("You WON Congratualtions !");
		
	}

	@Override
	public void showBoom() {
		System.out.println("BOOOOOM !!!");
	}

	@Override
	public void showReadyToStart() {
		System.out.println("Ready to start.");
	}

	private char readChar() {

		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);

		char inputChar = ' ';

		try {
			inputChar = (char) reader.read();
		} catch (IOException e) {
			System.out.println("An error occured while reading data!");
			System.exit(0);
		} catch (Exception e) {
			System.out.println("catched");
		}
		return inputChar;
	}

}
