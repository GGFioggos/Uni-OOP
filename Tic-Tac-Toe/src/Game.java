import java.util.Scanner;
import java.util.Random;

public class Game {
	private Gameboard board = new Gameboard();

	private Player player = new Player("Player", 'X');
	private Player CPU = new Player("CPU", 'O');

	public void startGame() {
		System.out.println("Please enter the column (A, B or C) and then the row (1, 2, or 3) of your move.");

		int i = 0;
		String coords;
		int intRow = 0;
		int intCol = 0;

		while (i < 8) {
			board.printBoard();
			// PLAYER MOVE
			if (i % 2 == 0) {
				System.out.println(player.getName() + " Move (" + player.getSymbol() + "):");

				// REPEAT INPUT VALIDATION UNTIL LEGAL AND NOT OCCUPIED.
				do {
					Scanner inScanner = new Scanner(System.in);

					coords = inScanner.nextLine();

					if (isLegalInput(coords)) {
						intCol = CharToInt(coords.charAt(0));
						intRow = Character.getNumericValue(coords.charAt(1));
						intRow--;

						if (board.isOccupied(intRow, intCol)) {
							System.out.println("Box is already occupied. Please try again.");
						}

					} else {
						System.out.println("Invalid Input: Please enter the column and row of you move (Example: A1)");
					}
				} while (!isLegalInput(coords) || (board.isOccupied(intRow, intCol)));
				player.makeMove(intRow, intCol, board);
				// CPU MOVE
			} else {
				System.out.println(CPU.getName() + " Turn");

				// REPEAT UNTIL RANDOM MOVE IS NOT OCCUPIED
				do {
					intRow = randomMove();
					intCol = randomMove();
				} while (board.isOccupied(intRow, intCol));

				CPU.makeMove(intRow, intCol, board);

			}
			i++;
		}
	}

	// CHECK IF GAME HAS ENDED
	private boolean hasEnded() {
		return false;
	}

	// RANDOM CPU MOVE
	private int randomMove() {
		return (new Random().nextInt(3));
	}

	// CONVERT FIRST CHAR OF INPUT TO INT
	private int CharToInt(char col) {
		if (col == 'A') {
			return 0;
		} else if (col == 'B') {
			return 1;
		}
		return 2;
	}

	// CHECK IF INPUT IS IN THE CORRECT FORMAT (A1)
	private boolean isLegalInput(String input) {
		if (input.length() > 2) {
			return false;
		}

		if (input.charAt(0) != 'A' && input.charAt(0) != 'B' && input.charAt(0) != 'C') {
			return false;
		}

		if (Character.getNumericValue(input.charAt(1)) > 3 || Character.getNumericValue(input.charAt(1)) <= 0) {
			return false;
		}
		return true;

	}
}
