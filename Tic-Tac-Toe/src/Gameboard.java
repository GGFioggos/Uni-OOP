
public class Gameboard {
	private final int DIMENSIONS = 3;
	private char[][] board = new char[DIMENSIONS][DIMENSIONS];

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

	public boolean isOccupied(int row, int col) {
		if (board[row][col] == 0) {
			return false;
		}
		return true;
	}

}
