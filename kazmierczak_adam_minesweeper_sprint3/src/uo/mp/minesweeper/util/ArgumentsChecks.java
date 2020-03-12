package uo.mp.minesweeper.util;

public class ArgumentsChecks {

	public static void isTrue(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}

	public static void isTrue(boolean condition) {
		if (!condition) {
			throw new IllegalArgumentException();
		}
	}

	public static void check(int arg1, int arg2) {
		if (arg1 <= 0 || arg2 <= 0) {
			throw new IllegalArgumentException();
		}
	}

	public static void check(char arg) {

		if ((int) arg < 48 || (int) arg > 57) {
			throw new IllegalArgumentException();
		}

	}

	public static void checkMove(char move, String message) {

		if (!(move == 's' || move == 'f' || move == 'u')) {
			throw new IllegalArgumentException(message);
		}

	}

	public static void check(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException();
		}

	}

}
