/**
 * CS312 Assignment 6.
 *
 * On my honor, Daniel Duru, this programming assignment is my own work
 * and I have not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 */

import java.util.Scanner;

public class Hangman {

	public static final int CHANCES = 5;

	public static void main(String[] args) {
		intro();
		PhraseBank phrases = buildPhraseBank(args);  
		Scanner keyboard = new Scanner(System.in);

		game(phrases, keyboard);
		keyboard.close();
	}

	
	public static void game(PhraseBank phrases, Scanner keyboard) {
		boolean play = true;
		String topic = phrases.getTopic();
		while (play) {
			String word = phrases.getNextPhrase();			
			gameStart(topic, word, keyboard);									
			play = gameEnd(keyboard);
		}
	}

	//starts the game by giving the player a word to guess.
	public static void gameStart(String topic, String word, Scanner key) {
		int incorrectGuess = 0;
		String current = censor(word);
		String guessList = "A--B--C--D--E--F--G--H--I--J--K--L--M--N--O--P--Q--R--S--T--U--V--W--X--Y--Z";
		System.out.println("\nI am thinking of a " + topic + " ...\n");
		while (!current.contains(word) && incorrectGuess != CHANCES) {			
			String guess = getGuess(key, guessList, current);
			boolean isLetter = checkList(word, guess);
			if (isLetter) {
				current = updateCurrent(word, current, guess);
			} else {
				incorrectGuess++;
			}
			guessList = updateGuessList(guessList, guess);
			winLose(incorrectGuess, word, current);
		}
	}

	//asks the user to input a value to determine whether the game will end
	public static boolean gameEnd(Scanner key) {
		boolean choice = false;
		System.out.println("Do you want to play again?");
		System.out.print("Enter 'Y' or 'y' to play again: ");
		String input = key.next();
		if (input.equals("Y") || input.equals("y")) {
			choice = true;
		}
		return choice;
	}
	
	//tells the user if they have won or lost depending on incorrect Guesses,
	//the word, and the current progress
	public static void winLose(int losses, String word, String progress) {
		System.out.println("\nNumber of wrong guesses so far: " + losses);
		if (losses == CHANCES) {
			System.out.println("You lose. The secret phrase was " + word);
		} else if (word.contains(progress)) {
			System.out.println("The phrase is " + word + ".");
			System.out.println("You win!!");
		}
	}

	//checks if the player's guess is correct
	public static String getGuess(Scanner key, String list, String current) {
		String guess = "*";
		System.out.println("The current phrase is " + current);
		while (!(guess.charAt(0) >= 'A' && guess.charAt(0) <= 'Z') 
				|| !list.contains(guess)) {
			System.out.println("\nThe letters you have not guessed yet are: ");
			System.out.println(list);
			System.out.print("\nEnter your next guess: ");
			guess = checkGuess(key, list);
		}
		return guess;
	}	

	//takes the input of the player and checks if it is valid
	public static String checkGuess(Scanner key, String list) {		
		String input = key.next();
		String input2 = input.toUpperCase();
		input2 = input2.substring(0,1);
		if (!(input2.charAt(0) >= 'A' && input2.charAt(0) <= 'Z')
				|| !list.contains(input2)) {
			System.out.print("\n" + input + " is not a valid guess.");
		}		
		return input2;
	}
	
	
	//checks if the letter chosen by the player was part of the secret phrase.
	public static boolean checkList(String word, String guess) {
		boolean onList = word.contains(guess);
		System.out.println("\nYou guessed " + guess + ".");
		if (onList) {
			System.out.println("\nThat is present in the secret phrase.");
		} else { //player's guess is not on the list
			System.out.println("\nThat is not present in the secret phrase.");
		}
		return onList;
	}

	//updates the player's progress by showing the letters currently guessed correctly
	public static String updateCurrent(String word, String current, String guess) {
		char letter = guess.charAt(0);
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) { 
				current = current.substring(0, i) + letter + current.substring(i+1, current.length());			
			}
		}
		return current;
	}

	//updates the list of letters chosen by the player
	public static String updateGuessList(String guessList, String guess) {
		char letter = guess.charAt(0);
		String newList = guessList;
		for (int i = 0; i < newList.length(); i++) {
			int end = newList.length() - 1;
			if (newList.charAt(i) == letter && i != end) { 
				newList = newList.substring(0, i) + newList.substring(i+3, newList.length());			
			} else if (newList.charAt(i) == letter && i == end){
				newList = newList.substring(0, i-2);
			}
		}
		return newList;
	}

	//censors the word for the player using astericks
	public static String censor(String word) {
		String censor = "";
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == '_') {
				censor += '_';
			} else {
				censor += '*';				
			}
		}
		return censor;
	}

	// Build the PhraseBank.
	// If args is empty or null, build the default phrase bank.
	// If args is not null and has a length greater than 0
	// then the first elements is assumed to be the name of the
	// file to build the PhraseBank from.
	public static PhraseBank buildPhraseBank(String[] args) {
		PhraseBank result;
		if (args == null || args.length == 0
				|| args[0] == null || args[0].length() == 0) {
			result =  new PhraseBank();
			/* CS312 students, uncomment the following line if you
			 * would like less predictable behavior from the PhraseBank
			 * during testing. NOTE, this line MUST be commented out
			 * in the version of the program you turn in
			 * or your will fail all tests.
			 */
			// result = new PhraseBank("HangmanMovies.txt", true); // MUST be commented out in version you submit.
		} else {
			result = new PhraseBank(args[0]);
		}

		return result;
	}

	// Print the intro to the program.
	public static void intro() {
		System.out.println("This program plays the game of hangman.");
		System.out.println();
		System.out.println("The computer will pick a random phrase.");
		System.out.println("Enter letters for your guess.");
		System.out.println("After 5 wrong guesses you lose.");
	}
}
