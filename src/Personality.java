/**
 * CS312 Assignment 8.
 * 
 * On my honor, Daniel Duru, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address: 
 *  UTEID: 
 *  TA name: 
 *  Number of slip days used on this assignment:  
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Personality {

	// CS312 Student- Add your constants here.
	public static final int QUESTIONS = 70;
	public static final double PERCENT_FACTOR = 100.0;
	public static final int DIMENSIONS = 4;
	public static final int QUESTION_SETS = 7;
	public static final int THRESHOLD = 50;
	// The main method to process the data from the personality tests
	public static void main(String[] args) {
		// do not make any other Scanners connected to System.in
		Scanner keyboard = new Scanner(System.in);
		Scanner fileScanner = getFileScanner(keyboard);		
		// CS312 students - your code and methods calls go here
		final int profiles = fileScanner.nextInt();
		fileScanner.nextLine();
		for (int i = 1; i <= profiles; i++) {
			String name = fileScanner.nextLine();
			String data = fileScanner.nextLine();		
			char[] answers = stringToArray(data);			
			int[] result = computeAnswers(answers);
			String personality = getPersonality(result);
			printResults(result, name, personality);
		}
		fileScanner.close();
		keyboard.close();
	}


	// CS312 Students add you methods here

	//prints the results
	public static void printResults(int[] results, String name, String personality) {
		String[] newResult = noAnswer(results);
		System.out.printf("%30s: %10s %10s %10s %10s = " + personality, name, newResult[0], 
				newResult[1], newResult[2], newResult[3]);
		System.out.println();
	}

	//gets the personality based on the test results
	public static String getPersonality(int[] result) {
		char[] personality = {'E', 'S', 'T', 'J'};
		char[] alt = {'I', 'N', 'F', 'P'};
		for (int i = 0; i < DIMENSIONS; i++) {
			if (result[i] > THRESHOLD) {
				personality[i] = alt[i];
			} else if (result[i] == THRESHOLD) {
				personality[i] = 'X';
			} else if (result[i] == -1) {
				personality[i] = '-';
			}
		}

		String personalityResult = "" + personality[0] + personality[1] + personality[2] + personality[3];
		return personalityResult;
	}

	//changes the list if there are no answers for a set of questions
	public static String[] noAnswer(int[] results) {
		String[] answers = new String[DIMENSIONS];
		for (int i = 0; i < DIMENSIONS; i++) {
			if (results[i] == -1) {
				answers[i] = "NO ANSWERS";
			} else {
				answers[i] = "" + results[i];
			}
		}
		return answers;
	}

	//converts the answer data from the file into an array of chars
	public static char[] stringToArray(String data) {
		char[] array = new char[QUESTIONS];
		for (int i = 0; i < QUESTIONS; i++) {
			array[i] = data.charAt(i);
		}
		return array;
	}

	//dimension 0 =  extrovert/introvert, 1 = sensation/intuition
	//2 = thinking/feeling, 3 = judging/perceiving
	public static int[] computeAnswers(char[] answers) {
		int[] results = new int[DIMENSIONS];
		int[] totals = new int [DIMENSIONS];
		for (int i = 0; i < QUESTIONS; i++) {
			char answer = answers[i];
			int index = i % QUESTION_SETS;
			updateArrays(answer, totals, index, results);
		}
		getPct(results, totals);
		return results;
	}

	//updates the arrays based on the answers given
	public static void updateArrays(char answer, int[] totals, int index,
			int[] results) {
		if (index == 0) {
			totals[0] += checkAnswer(answer);
			results[0] += checkAnswer2(answer);    		 
		} else if (index == 1 || index == 2) {
			totals[1] += checkAnswer(answer);
			results[1] += checkAnswer2(answer);
		} else if (index == 3 || index == 4) {
			totals[2] += checkAnswer(answer);
			results[2] += checkAnswer2(answer);
		} else { //index == 5 || index == 6)
			totals[3] += checkAnswer(answer);
			results[3] += checkAnswer2(answer);
		}
	}

	//updates the total value of answers
	public static int checkAnswer(char answer) {
		int value = 0;
		if ((answer == 'A' || answer == 'a') || 
				(answer == 'B' || answer == 'b')) {
			return 1;
		}
		return value;	
	}

	//updates whether the answer was a b answer or not
	public static int checkAnswer2(char answer) {
		if (answer == 'B' || answer == 'b') {
			return 1;
		}
		return 0;
	}

	//gets the percentage of B that the user chose unless they did not answer
	//in the case of no answer, the stored value will equal -1
	public static void getPct(int[] results, int[] total) {
		for (int i = 0; i < DIMENSIONS; i++) {
			if(total[i] > 0) {
				double percentage = PERCENT_FACTOR * results[i] / total[i];
				results[i] = (int) (percentage + 0.5); 
			} else { //total = 0
				results[i] = -1;
			}
		}
	}
	
	// Method to choose a file.
	// Asks user for name of file. 
	// If file not found create a Scanner hooked up to a dummy set of data
	// Example use: 
	public static Scanner getFileScanner(Scanner keyboard){
		Scanner result = null;
		try {
			System.out.print("Enter the name of the file with"
					+ " the personality data: ");
			String fileName = keyboard.nextLine().trim();
			System.out.println();
			result = new Scanner(new File(fileName));
		} catch(FileNotFoundException e) {
			System.out.println("Problem creating Scanner: " + e);
			System.out.println("Creating Scanner hooked up to default data " 
					+ e);
			String defaultData = "1\nDEFAULT DATA\n"
					+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
					+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
			result = new Scanner(defaultData);
		}
		return result;
	}
}
