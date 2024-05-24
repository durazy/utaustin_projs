import java.util.Scanner;

/**
 * CS312 Assignment 10.
 *
 * On my honor, Daniel Duru, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * Program that allows two people to play Connect Four.
 */


public class ConnectFour {

	// CS312 Students, add you constants here
	public final static int ROWS = 6;
	public final static int COLS = 7;
	//Players
	public final static int P1 = 1;
	public final static int P2 = 2;
	//Each player's checkers
	public final static char C1 = 'r';
	public final static char C2 = 'b';

	public static void main(String[] args) {
		intro();

		// complete this method
		// Recall make and use one Scanner conected to System.in
		Scanner userInput = new Scanner(System.in);
		char[][] gameBoard = createBoard();
		String p1 = getPlayer(P1, userInput);
		String p2 = getPlayer(P2, userInput);
		boolean gameWinner = false;
		int round = 0;
		printBoard(gameBoard, gameWinner);
		while (!gameWinner) {
			//run rounds
			runRound(p1, p2, userInput, gameBoard, round);      	
			//check if game is over
			gameWinner = isGameOver(gameBoard, p1, p2);      	
			//update the board
			printBoard(gameBoard, gameWinner);
			//switches players
			round++;
		}       
	}

	// CS312 Students, add your methods

	//checks if there is a winner or if the board is full
	public static boolean isGameOver(char[][] board, String p1, String p2) {
		String winner = "none";
		//check win conditions
		winner = getWinner(board, p1, p2);
		if (!winner.equals("none")) {
			whoWon(winner);
			return true;
		}
		return false;    	
	}

	//prints the result
	public static void whoWon(String winner) {
		if (winner.equals("draw")) {
			System.out.print("The game is a draw");
		} else {
			System.out.println("\n" + winner + " wins!!");
		}
	}

	//checks the win conditions and determines a winner
	public static String getWinner(char[][] board, String p1, String p2) {
		boolean full = isBoardFull(board);
		boolean winOne = checkPlayer(board, C1);
		boolean winTwo = checkPlayer(board, C2);
		if (winOne) {
			return p1;
		} else if (winTwo) {
			return p2;
		} else if(full) {
			return "draw";
		}
		return "none";
	}

	//checks if the board is completely full
	public static boolean isBoardFull(char[][] board) {
		int count = 0;
		for (int i = 0; i < COLS; i++) {
			if (board[0][i] != '.') {
				count++;
			}
		}
		if (count == 7) {
			return true;
		} // else
		return false;
	}

	//checks all possible win conditions
	public static boolean checkPlayer(char[][] board, char piece) {
		//check down
		for (int col = 0; col < COLS; col++) { // for every row above row 4 check down
			for (int row = 0; row < 3; row++) {
				if (board[row][col] == piece && board[row + 1][col] == piece && board[row + 2][col] == piece
						&& board[row + 3][col] == piece) {
					return true;
				}
			}   	
		}    	
		//check right
		for (int col = 0; col < 4; col++) { // for every col from 1-4
			for (int row = 0; row < ROWS; row++) {
				if (board[row][col] == piece && board[row][col  + 1] == piece && board[row][col + 2] == piece
						&& board[row][col + 3] == piece) {
					return true;
				}
			}   	
		}
		//check southwest
		for (int col = 3; col < COLS; col++) { // every col from 4-7 and row 1-3
			for (int row = 0; row < 3; row++) {
				if (board[row][col] == piece && board[row - 1][col - 1] == piece && board[row - 2][col - 2] == piece
						&& board[row - 3][col + 3] == piece) {
					return true;
				}
			}   	
		}
		//check southeast
		for (int col = 0; col < 4; col++) { // every col 1-4 and row 1-3
			for (int row = 0; row < 3; row++) {
				if (board[row][col] == piece && board[row + 1][col + 1] == piece && board[row + 2][col + 2] == piece
						&& board[row + 3][col + 3] == piece) {
					return true;
				}
			}   	
		}
		return false;
	}


	//runs each round
	public static void runRound(String p1, String p2, Scanner console, char[][] board,
			int round) {
		//method to ask for turns and check round
		int turn = whoseTurn(round, p1, p2);
		//method to check the if the input is legal, valid, or an integer
		int pos = getChecker(turn, p1, p2, console, board);
		//place the piece on the board
		placePiece(turn, pos, board);
	}

	//places the piece from the user's input on the board
	public static void placePiece(int turn, int pos, char[][] board) {
		boolean spot = false;
		char piece;
		if (turn == 1) {
			piece = C1;
		} else {
			piece = C2;
		}
		for (int i = ROWS - 1; i >= 0 && !spot; i--) {
			if (board[i][pos] == '.') {
				board[i][pos] = piece;
				spot = true;
			}
		}
	}

	//gets the position for the checker piece of the player
	//has checks to make sure input is valid
	public static int getChecker(int turn, String p1, String p2, Scanner console, char[][] board) {
		String player;
		if(turn == 1) {
			player = p1;
		} else { //turn == 2
			player = p2;
		}
		int pos = inputCheck(player, console, board);
		return pos;
	}

	//gets the players turns based on which round it is
	public static int whoseTurn(int round, String p1, String p2) {
		String player;
		char piece;
		int pWho = 0;
		if (round % 2 == 0) {
			player = p1;
			piece = C1;
			pWho = 1;
		} else {
			player = p2;
			piece = C2;
			pWho = 2;
		}
		System.out.println("\n" + player + " it is your turn.");
		System.out.println("Your pieces are the " + piece + "'s.");
		return pWho;
	}

	//uses a variety of checks to ensure a valid number
	public static int inputCheck(String player, Scanner console, char[][] board) {
		String prompt = player + ", enter the column to drop your checker: ";
		System.out.print(prompt);
		boolean valid = false;    	
		int input = 0;
		while (!valid) {
			input = getInt(console, prompt);    		
			valid = isValid(input, board); 
			if(!valid) {
				System.out.print(prompt);
			}
		}
		return input;
	}

	//checks whether the input is valid or not
	public static boolean isValid(int input, char[][] board) {
		if(!(input >= 1 && input <= 7)) {
			System.out.println("\n" + input + " is not a valid column.");
			return false;
		} else if (board[0][input] != '.') {
			// checks if the column is full or not.
			System.out.println("\n" + input + "is not a legal column. That column is full.");
			return false;
		}    	
		return true;
	}

	//gets the player name and sets them to player one or two
	public static String getPlayer(int num, Scanner console) {
		System.out.println();
		System.out.print("Player " + num + " enter your name: ");  	
		return console.nextLine();
	}

	//creates the board by turning the array from empty spaces to '.' chars
	public static char[][] createBoard() {
		char[][] board = new char[ROWS][COLS];
		for(int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				board[row][col] = '.';
			}
		}
		return board;
	}

	//prints out the board every round
	//shows empty spaces, placed chips, and labels
	public static void printBoard(char[][] board, boolean gameOver) {
		//labels
		if (gameOver) {
			System.out.println("\nFinal Board");
		} else {
			System.out.println("\nCurrent Board");
		}
		System.out.println("1 2 3 4 5 6 7  column numbers");
		//creates the grid
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}

	// show the intro
	public static void intro() {
		System.out.println("This program allows two people to play the");
		System.out.println("game of Connect four. Each player takes turns");
		System.out.println("dropping a checker in one of the open columns");
		System.out.println("on the board. The columns are numbered 1 to 7.");
		System.out.println("The first player to get four checkers in a row");
		System.out.println("horizontally, vertically, or diagonally wins");
		System.out.println("the game. If no player gets fours in a row and");
		System.out.println("and all spots are taken the game is a draw.");
		System.out.println("Player one's checkers will appear as r's and");
		System.out.println("player two's checkers will appear as b's.");
		System.out.println("Open spaces on the board will appear as .'s.\n");
	}


	// Prompt the user for an int. The String prompt will
	// be printed out. I expect key is connected to System.in.
	public static int getInt(Scanner key, String prompt) {
		while(!key.hasNextInt()) {
			String notAnInt = key.nextLine();
			System.out.println("\n" + notAnInt + " is not an integer.");
			System.out.print(prompt);
		}
		int result = key.nextInt();
		key.nextLine();
		return result;
	}
}
