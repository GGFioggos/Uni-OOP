
public class Gameboard {
	private final int DIMENSIONS = 3;
	private char[][] board = new char[DIMENSIONS][DIMENSIONS];

	// PRINT CURRENT STATE OF BOARD
	public void printBoard() {
		System.out.println("   A B C");
		for (int i = 0; i < DIMENSIONS; i++) {
			System.out.print(i + 1 + " |");
			for (int j = 0; j < DIMENSIONS; j++) {
				System.out.print(board[i][j] + "|");
			}
			System.out.println("");
		}
	}

	public void receiveMove(int row, int col, char symbol) {
		board[row][col] = symbol;
	}

	// CHECK IF BOARD BOX IS OCCUPIED
	public boolean isOccupied(int row, int col) {
		if (board[row][col] == 0) {
			return false;
		}
		return true;
	}

	// CHECK IF GAME HAS ENDED
	public boolean hasEnded() {
		// CHECK HORIZONTAL
		for (int i = 0; i < DIMENSIONS; i++) {
			if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != 0) {
				return true;
			}
		}

		// CHECK VERTICAL
		for (int i = 0; i < DIMENSIONS; i++) {
			if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != 0) {
				return true;
			}
		}

		// CHECK DIAGONAL
		if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != 0) {
			return true;
		}

		if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != 0) {
			return true;
		}

		return false;

	}

}
