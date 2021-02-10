import java.util.Arrays;
import java.util.Scanner;

public class game {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int i,j;
		int N = 10;
		int M = 20;
		
		boolean[][] board = Board.createBoard(N, M); // mines placement
		boolean[][] moves = Board.createBoard(N, 0); //keeps track of player moves
		boolean[][] auxBoard = Board.createAuxBoard(board); //auxillary board for neighbor mines counting
		int[][] minesMap = Board.minesMap(auxBoard); // # of neighbor mines
		String[][] playerBoard = Board.createEmptyBoard(N); // what player see
		
		
		
	//	System.out.println(Arrays.deepToString(auxBoard));
	//	Board.booleanPrinter(board);
	//	Board.gamePrint(playerBoard);
		//stestuj
		
		while(true) {
			Board.gamePrint(playerBoard);
			
			//winning condition
			int counter = Board.counter(moves);
			if (counter == N * N - M) {
				
				for (i = 0; i < N; i++) {
					
					for (j = 0; j < N; j++) {
						
						if (playerBoard[i][j] == "\u25A0") {
							
							playerBoard[i][j] = "•";
							
						}
						
						
					}
					
				}
				
				System.out.println("Winner!");
				System.exit(666);
				
			}
			
			//validate input of chosen cell (has to be a number between 0 and N)
			do {
				System.out.println("i coordinate (0 - " + N + "): ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Wrong number");
					String input = sc.next();
				}
				i = sc.nextInt();
			} while (i < 0 || i > N);
			
			do {
				System.out.println("j coordinate (0 - " + N + "): ");
				
				while(!sc.hasNextInt()) {
					System.out.println("Wrong number");
					String input = sc.next();
				}
				j = sc.nextInt();
			} while (j < 0 || j > N);
			
			if (moves[i][j]) {
				System.out.println("You checked " + i + ", " + j + " before!");
			} else {
				
				if (board[i][j]) {
					
					playerBoard[i][j] = "•";
					Board.gamePrint(playerBoard);
					
					Board.losePrint(playerBoard, board, minesMap);
					
					System.out.println("BOOM!");
					System.exit(0);
					
				} else if (!board[i][j] && minesMap[i][j] == 0) {
					
//					playerBoard[i][j] = " ";
//					moves[i][j] = true;
					Board.checkZero(minesMap, i, j, playerBoard, moves);
					
				} else if (!board[i][j]) {
					
					playerBoard[i][j] = Integer.toString(minesMap[i][j]);
					moves[i][j] = true;
					
				}
				
			}
		}
		
		
	}

}







