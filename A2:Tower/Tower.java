/*
 *  CS312 Assignment 2.
 *  On my honor, Daniel Duru, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 */

public class Tower {

	// CS312 students, DO NOT ALTER THE FOLLOWING LINE except for the 
	// value of the literal int.
	// You may change the literal int assigned to SIZE to any value from 2 to 100.
	// In the final version of the program you submit set the SIZE to 3.
	public static final int SIZE = 4;

	// main method that creates the tower
	public static void main(String[] args) {
		top();
		middle();
		bottom();
	}

	/* 
	 * creates the upper section of the tower
	 * this method uses a line method for the top and bottom and uses loops for the inner pattern
	 */
	public static void top() {
		topLine();
		//loops the part between the lines of #'s depending on the size
		for (int line = 1; line <= (SIZE - 1) * 2; line++) {
			topSpace();
			for (int i = 1; i <= (2 * SIZE) - 1; i++) {
				System.out.print("|");
			}
			System.out.println();
		}
		topLine();
	}

	// creates the spaces for each part of the top of the tower
	public static void topSpace() {
		for (int i = 1; i <= SIZE*4 + 2; i++) {
			System.out.print(" ");
		}
	}

	// creates the lines of #'s used at the top and bottom of the top method
	public static void topLine() {
		topSpace();
		for (int i = 1; i <= ((2*SIZE) - 1); i++) {
			System.out.print("#");
		}
		System.out.println();
	}

	/*
	 * Method creates the middle part of the tower
	 * loops the middleLine method with a line pattern
	 */
	public static void middle() {
		middleLine();
		for (int lines = 1; lines <= (SIZE * SIZE); lines++) {
			middleSpace();
			System.out.print("|-");
			for (int i = 1; i <= SIZE; i++) {
				System.out.print("O-");
			}
			System.out.println("|");
			middleLine();
		}    		
	}

	//creates the space used for the middle segment of the tower
	public static void middleSpace() {
		for (int i = 1; i <= SIZE*4; i++) {
			System.out.print(" ");
		}
	}

	// method creates lines in the middle part of the tower that separate the lines with a pattern
	public static void middleLine() {
		middleSpace();
		for (int i = 1; i <= (SIZE*2) + 3; i++) {
			System.out.print("~");
		}
		System.out.println();
	}

	// method puts the upper and lower parts of the base together
	public static void bottom() {
		bottomOne();
		bottomTwo();   	
	}

	/*
	 *  method creates the upper part of the base of the tower by looping spaces based 
	 *  on the number of lines there are and the inner pattern based on the SIZE and line count
	 */
	public static void bottomOne() {
		int lineCount = (SIZE / 2) + 1;
		for (int lines = lineCount; lines >= 1; lines--) {
			for (int i = 1; i <= (lines - 1) * 3; i++) {
				System.out.print(" ");
			}
			System.out.print("/\"");
			/* This loop creates quotation marks based on the line count and overall size
			 * the rate changes based on whether SIZE is an even or odd number
			 */
			for (int i = 1; i <= (3*((SIZE-1)/2) + SIZE*2 + 3*(lineCount - lines + 1)); i++) {
				System.out.print("'\"");
			}
			System.out.println("\\");
		}
	}

	// method creates the lower base of the tower by looping a pattern dependent on SIZE
	public static void bottomTwo() {
		for (int lines = 1; lines <= SIZE; lines++) {
			System.out.print("/\"");
			for (int i = 1; i <= SIZE*5; i++) {
				System.out.print("O\"");
			}
			System.out.println("\\");
		}    		    		
	}
}
