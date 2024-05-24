import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * CS312 Assignment 7.
 * 
 * On my honor, Daniel Duru, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play determine the extend of home field advantage in sports.
 *
 *  email address:
 *  UTEID:
 *  Unique 5 digit course ID:
 *  Grader name:
 *  Number of slip days used on this assignment:
 */

/**
 * Analysis of results. Include your write-up of results and analysis here:
 *
 */

public class HomeField {

	// Ask the user for the name of a data file and process
	// it until they want to quit.
	public static void main(String[] args) throws IOException {
		System.out.println("A program to analyze home field advantage in sports.");
		System.out.println();
		// CS312 students. Do not create any other Scanners connected to System.in.
		// Pass keyboard as a parameter to all the methods that need it. 
		Scanner keyboard = new Scanner(System.in);

		// CS312 students - Add your code here
		boolean runAgain = true;
		while (runAgain) {
			Scanner source = getSource(keyboard);
			printHeader(source);
			dataUse(source);
			runAgain = isDone(keyboard);
		}
		keyboard.close();
	}

	// CS312 Students - Add your methods here.

	//separates the data and uses methods to calculate the results
	public static void dataUse(Scanner source) {		
		int totalGames = 0;
		int homeGames = 0;
		int homeWins = 0;
		double margin = 0;
		while(source.hasNextLine()) {
			totalGames++;
			String data = source.nextLine();
			boolean homeGame = isHomeGame(data);
			if (homeGame) {
				homeGames++;
				boolean homeWin = isWinner(data);
				if (homeWin) {
					homeWins++;
				}
				margin += findMargin(data);
			}
		}
		results(totalGames, homeGames, homeWins, margin);
	}

	//gets the sport and season and print them in the header
	public static void printHeader(Scanner source) {
		String sport = source.nextLine();
		String season = source.nextLine();
		System.out.println("\n**********   " + sport + " --- " + season + "   **********");
		System.out.println("\nHOME FIELD ADVANTAGE RESULTS\n");	
	}

	//uses the total games and home wins to determine the advantage results
	public static void results(int totalGames, int homeGames, int homeWins,
			double margin) {
		double percentFactor = 100.0;
		double roundFactor = 10.0;
		double homePercent = Math.round(roundFactor * percentFactor * homeGames / totalGames) / roundFactor;
		double wonPercent = Math.round(roundFactor * percentFactor * homeWins / homeGames) / roundFactor;
		double avgMargin = Math.round(margin / homeGames * percentFactor) / percentFactor;
		System.out.printf("Total number of games: %,d", totalGames);
		System.out.printf("\nNumber of games with a home team: %,d", homeGames);
		System.out.printf("\nPercentage of games with a home team: %.1f%%", homePercent);
		System.out.printf("\nNumber of games the home team won: %,d", homeWins);
		System.out.printf("\nHome team win percentage: %.1f%%", wonPercent);
		System.out.printf("\nHome team average margin: %.2f", avgMargin);
		System.out.println();
	}

	//checks the data to see if the home team is the winner
	public static boolean isWinner(String source) {
		Scanner data = new Scanner(source);
		String year = data.next();
		String team1 = fullName(data);
		int score1 = data.nextInt();
		String team2 = fullName(data);
		int score2 = data.nextInt();
		data.close();	
		if ((team1.contains("@") && score1 > score2) 
				|| (team2.contains("@") && score2 > score1)) {
			return true;
		}
		return false;
	}

	//gets the name of the team so that the data reading is easier
	public static String fullName(Scanner source) {
		String name = source.next();
		while(!source.hasNextInt() && source.hasNext()) {
			name += "_" + source.next();
		}
		return name;
	}

	// finds the difference in score between the teams.
	public static double findMargin(String source) {
		Scanner data = new Scanner(source);
		String year = data.next();
		String team1 = fullName(data);
		int score1 = data.nextInt();
		String team2 = fullName(data);
		int score2 = data.nextInt();
		data.close();
		if (team1.contains("@")) {
			return score1 - score2;
		} else {
			return score2 - score1;
		}
	}

	//checks if the game had a home team and returns true if so
	public static boolean isHomeGame(String data) {
		if (data.contains("@")) {
			return true;
		}
		return false;
	}
	//asks the use for a file and if the file doesn't exists, it
	//prompts the user again.
	public static Scanner getSource(Scanner console) 
			throws FileNotFoundException {
		System.out.print("Enter the file name: ");
		File source = new File(console.nextLine());
		while (!source.canRead()) {
			System.out.println("Sorry, that file does not exist");
			System.out.println("Enter the file name: ");
			source = new File(console.nextLine());
		}
		return new Scanner(source);
	}

	//asks the user if they would like to check another set of data
	//if the user refuses the program ends
	public static boolean isDone(Scanner console) {
		System.out.println("\nDo you want to check another data set?");
		System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
		String answer = console.nextLine();
		answer = answer.toLowerCase();

		if (answer.length() == 0) {
			return false;
		}
		
		if (answer.charAt(0) == 'y' && answer.length() < 2) {
			System.out.println();
			return true;
		} else {
			return false;
		}		
	}
}
