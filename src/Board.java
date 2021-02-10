import java.util.Arrays;

public class Board {

	public static boolean[][] createBoard(int N, int M) {
				
		int i = 0;
		int j = 0;
		int k = 0;
		
		boolean[][] board = new boolean[N][N]; //board generated - all false
		
		//generate mines
		
		while (k < M) {
			
			i = (int) Math.floor(N * Math.random());
			j = (int) Math.floor(N * Math.random());
			
			if (board[i][j] == false) {
				board[i][j] = true;	
				k++;
			}
		}
		return board;
	}
	
	public static String[][] createEmptyBoard(int N) {
		

		
		String[][] emptyBoard = new String[N][N]; //board generated - all false
		
		//generate 
		
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++) {
				
				emptyBoard[i][j] = "\u25A0";
				
			}
			
		}
		

		return emptyBoard;
	}
	
	public static boolean[][] createAuxBoard(boolean[][] board) {
		
		int N = board.length;
		
		//auxillary boolean board (N + 2) x (N + 2)
		
		boolean[][] auxBoard = new boolean[N + 2][N + 2];
		
		for (int i = 1; i < N + 1; i++) {
			
			for(int j = 1; j < N + 1; j++) {
				
				auxBoard[i][j] = board[i - 1][j - 1];
				
			}
			
		}
		return auxBoard;
	}
	
	//count mines around given [i,j] position
	public static byte countMines(boolean[][] auxBoard, int i, int j) {
	
		byte count = 0;
		i++;
		j++;
		
		if (auxBoard[i - 1][j - 1]) {
			count++;
		} 
		if (auxBoard[i - 1][j]) {
			count++;
		}
		if (auxBoard[i - 1][j + 1]) {
			count++;
		}
		if (auxBoard[i + 1][j - 1]) {
			count++;
		}
		if (auxBoard[i + 1][j]) {
			count++;
		}
		if (auxBoard[i + 1][j + 1]) {
			count++;
		}
		if (auxBoard[i][j - 1]) {
			count++;
		}
		if (auxBoard[i][j + 1]) {
			count++;
		}		
		return count;
	}
	
	public static int[][] minesMap(boolean[][] auxBoard) {
		
		int N = auxBoard.length - 2;
		int[][] countedMines = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			
			for(int j = 0; j < N; j++) {
				
				countedMines[i][j] = Board.countMines(auxBoard, i, j);
				
			}
		}
		return countedMines;
	}
	
	public static void booleanPrinter(boolean[][] board) {
		
		int N = board.length;
		System.out.println();

		System.out.print("~ ");
		
		for (int i = 0; i < N; i++) {

			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i = 0; i < N; i++) {
			
			System.out.print(i + " ");
			
			for (int j = 0; j < N; j++) {
				
				if (board[i][j] == true) {
					System.out.print("\u25A1" + " ");
				} else if (board[i][j] == false) {
					System.out.print("\u25A0" + " ");
					
				}
				
			}
			System.out.println();
		}
		
	}
	
public static void printBoard(boolean[][] board, byte[][] minesMap) {
		
		int N = board.length;
		System.out.println();

		System.out.print("~ ");
		
		for (int i = 0; i < N; i++) {

			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i = 0; i < N; i++) {
			
			System.out.print(i + " ");
			
			for (int j = 0; j < N; j++) {
				
				if (board[i][j] == true) {
					System.out.print("\u25A1" + " ");
				} else if (board[i][j] == false) {
					System.out.print("\u25A0" + " ");
					
				}
				
			}
			System.out.println();
		}
		
	}

	public static void gamePrint(String[][] playerBoard) {
		
		int N = playerBoard.length;
		System.out.println();

		System.out.print("~ ");
		
		for (int i = 0; i < N; i++) {

			System.out.print(i + " ");
		}
		System.out.println();
		
		for (int i = 0; i < N; i++) {
			
			System.out.print(i + " ");
			
			for (int j = 0; j < N; j++) {
				
				System.out.print(playerBoard[i][j] + " ");
				
			}
			System.out.println();
		}
		
	}
	
	public static int counter(boolean[][] moves) {
		
		int N = moves.length;
		int counter = 0;
		
		for (int i = 0; i < N; i++) {
			
			for ( int j = 0; j < N; j++) {
				
				if (moves[i][j] == true) {
				
					counter++;
					
				}
			}
		}
		return counter;
		
	}
	
	public static void losePrint(String[][] playerBoard, boolean[][] board, int[][] minesMap) {

		int N = board.length;
		
		for (int i = 0; i < N; i++) {
			
			for (int j = 0; j < N; j++) {
				
				if (board[i][j]) {
					
					playerBoard[i][j] = "•";
					
				} else if (!board[i][j] && minesMap[i][j] != 0) {
					
					playerBoard[i][j] = Integer.toString(minesMap[i][j]);
					
				} else if (!board[i][j] && minesMap[i][j] == 0) {
					
					playerBoard[i][j] = " ";
					
				}
				
			}
			
		}
		Board.gamePrint(playerBoard);
		
	}
	
	//uncovers series of cells without neighboring mines
	public static void checkZero(int[][] minesMap, int i, int j, String[][] playerMap, boolean[][] moves) {

		int N = minesMap.length;
		System.out.println();
		//middle zone
		if (minesMap[i][j] == 0 && 0 < i && i < N - 1 && 0 < j && j < N - 1 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i + 1, j - 1, playerMap, moves);
			checkZero(minesMap, i + 1, j, playerMap, moves);
			checkZero(minesMap, i + 1, j + 1, playerMap, moves);
			checkZero(minesMap, i - 1, j - 1, playerMap, moves);
			checkZero(minesMap, i - 1, j, playerMap, moves);
			checkZero(minesMap, i - 1, j + 1, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i, j - 1, playerMap, moves);
			
			if (playerMap[i + 1][j - 1] == "\u25A0") {
				playerMap[i + 1][j - 1] = Integer.toString(minesMap[i + 1][j - 1]);
				moves[i + 1][j - 1] = true;
			}
			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			if (playerMap[i + 1][j + 1] == "\u25A0") {
				playerMap[i + 1][j + 1] = Integer.toString(minesMap[i + 1][j + 1]);
				moves[i + 1][j + 1] = true;
			}
			if (playerMap[i - 1][j - 1] == "\u25A0") {
				playerMap[i - 1][j - 1] = Integer.toString(minesMap[i - 1][j - 1]);
				moves[i - 1][j - 1] = true;
			}
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			if (playerMap[i - 1][j + 1] == "\u25A0") {
				playerMap[i - 1][j + 1] = Integer.toString(minesMap[i - 1][j + 1]);
				moves[i - 1][j + 1] = true;
			}
			if (playerMap[i][j - 1] == "\u25A0") {
				playerMap[i][j - 1] = Integer.toString(minesMap[i][j - 1]);
				moves[i][j - 1] = true;
			}
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
	

			
			
			
		//top left corner
		} else if (minesMap[i][j] == 0 && i == 0 && j == 0 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i + 1, j, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i + 1, j + 1, playerMap, moves);
			
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
			if (playerMap[i + 1][j + 1] == "\u25A0") {
				playerMap[i + 1][j + 1] = Integer.toString(minesMap[i + 1][j + 1]);
				moves[i + 1][j + 1] = true;
			}
			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			

		//top right corner
		} else if (minesMap[i][j] == 0 && i == 0 && j == N - 1 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i + 1, j, playerMap, moves);
			checkZero(minesMap, i, j - 1, playerMap, moves);
			checkZero(minesMap, i + 1, j - 1, playerMap, moves);

			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			if (playerMap[i + 1][j - 1] == "\u25A0") {
				playerMap[i + 1][j - 1] = Integer.toString(minesMap[i + 1][j - 1]);
				moves[i + 1][j - 1] = true;
			}	
			if (playerMap[i + 1][j - 1] == "\u25A0") {
				playerMap[i + 1][j - 1] = Integer.toString(minesMap[i + 1][j - 1]);
				moves[i + 1][j - 1] = true;
			}
			
		//bottom left corner
		} else if (minesMap[i][j] == 0 && i == N - 1 && j == 0 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i - 1, j, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i - 1, j + 1, playerMap, moves);
			
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
			if (playerMap[i - 1][j + 1] == "\u25A0") {
				playerMap[i - 1][j + 1] = Integer.toString(minesMap[i - 1][j + 1]);
				moves[i - 1][j + 1] = true;
			}

		//bottom right corner
		} else if (minesMap[i][j] == 0 && i == N - 1 && j == N - 1 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i - 1, j - 1, playerMap, moves);
			checkZero(minesMap, i, j - 1, playerMap, moves);
			checkZero(minesMap, i - 1, j, playerMap, moves);
			
			if (playerMap[i - 1][j - 1] == "\u25A0") {
				playerMap[i - 1][j - 1] = Integer.toString(minesMap[i - 1][j - 1]);
				moves[i - 1][j - 1] = true;
			}
			if (playerMap[i][j - 1] == "\u25A0") {
				playerMap[i][j - 1] = Integer.toString(minesMap[i][j - 1]);
				moves[i][j - 1] = true;
			}
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			
		//left side
		} else if (minesMap[i][j] == 0 && j == 0 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i + 1, j, playerMap, moves);
			checkZero(minesMap, i - 1, j, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i - 1, j + 1, playerMap, moves);
			checkZero(minesMap, i + 1, j + 1, playerMap, moves);
			
			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
			if (playerMap[i - 1][j + 1] == "\u25A0") {
				playerMap[i - 1][j + 1] = Integer.toString(minesMap[i - 1][j + 1]);
				moves[i - 1][j + 1] = true;
			}
			if (playerMap[i + 1][j + 1] == "\u25A0") {
				playerMap[i + 1][j + 1] = Integer.toString(minesMap[i + 1][j + 1]);
				moves[i + 1][j + 1] = true;
			}
			
		//right side
		} else if (minesMap[i][j] == 0 && j == N - 1 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i + 1, j, playerMap, moves);
			checkZero(minesMap, i - 1, j, playerMap, moves);
			checkZero(minesMap, i, j - 1, playerMap, moves);
			checkZero(minesMap, i + 1, j - 1, playerMap, moves);
			checkZero(minesMap, i - 1, j - 1, playerMap, moves);
			
			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			if (playerMap[i][j - 1] == "\u25A0") {
				playerMap[i][j - 1] = Integer.toString(minesMap[i][j - 1]);
				moves[i][j - 1] = true;
			}
			if (playerMap[i + 1][j - 1] == "\u25A0") {
				playerMap[i + 1][j - 1] = Integer.toString(minesMap[i + 1][j - 1]);
				moves[i + 1][j - 1] = true;
			}
			if (playerMap[i - 1][j - 1] == "\u25A0") {
				playerMap[i - 1][j - 1] = Integer.toString(minesMap[i - 1][j - 1]);
				moves[i - 1][j - 1] = true;
			}
			
		//top side
		} else if (minesMap[i][j] == 0 && i == 0 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i, j - 1, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i + 1, j - 1, playerMap, moves);
			checkZero(minesMap, i + 1, j + 1, playerMap, moves);
			checkZero(minesMap, i + 1, j, playerMap, moves);
			
			if (playerMap[i][j - 1] == "\u25A0") {
				playerMap[i][j - 1] = Integer.toString(minesMap[i][j - 1]);
				moves[i][j - 1] = true;
			}
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
			if (playerMap[i + 1][j - 1] == "\u25A0") {
				playerMap[i + 1][j - 1] = Integer.toString(minesMap[i + 1][j - 1]);
				moves[i + 1][j - 1] = true;
			}
			if (playerMap[i + 1][j + 1] == "\u25A0") {
				playerMap[i + 1][j + 1] = Integer.toString(minesMap[i + 1][j + 1]);
				moves[i + 1][j + 1] = true;
			}
			if (playerMap[i + 1][j] == "\u25A0") {
				playerMap[i + 1][j] = Integer.toString(minesMap[i + 1][j]);
				moves[i + 1][j] = true;
			}
			
		//bottom side
		} else if (minesMap[i][j] == 0 && i == N - 1 && moves[i][j] == false) {
			playerMap[i][j] = " ";
			moves[i][j] = true;
			
			checkZero(minesMap, i, j - 1, playerMap, moves);
			checkZero(minesMap, i, j + 1, playerMap, moves);
			checkZero(minesMap, i - 1, j - 1, playerMap, moves);
			checkZero(minesMap, i - 1, j, playerMap, moves);
			checkZero(minesMap, i - 1, j + 1, playerMap, moves);
			
			if (playerMap[i][j - 1] == "\u25A0") {
				playerMap[i][j - 1] = Integer.toString(minesMap[i][j - 1]);
				moves[i][j - 1] = true;
			}
			if (playerMap[i][j + 1] == "\u25A0") {
				playerMap[i][j + 1] = Integer.toString(minesMap[i][j + 1]);
				moves[i][j + 1] = true;
			}
			if (playerMap[i - 1][j - 1] == "\u25A0") {
				playerMap[i - 1][j - 1] = Integer.toString(minesMap[i - 1][j - 1]);
				moves[i - 1][j - 1] = true;
			}
			if (playerMap[i - 1][j] == "\u25A0") {
				playerMap[i - 1][j] = Integer.toString(minesMap[i - 1][j]);
				moves[i - 1][j] = true;
			}
			if (playerMap[i - 1][j + 1] == "\u25A0") {
				playerMap[i - 1][j + 1] = Integer.toString(minesMap[i - 1][j + 1]);
				moves[i - 1][j + 1] = true;
			}
			
			
		}
		
		
		
		
	}
	
	
}
