/*
 * CS312 Assignment 1.
 * On my honor, Daniel Duru, this programming assignment is my own work.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 */

public class Song {

	// Prints out the lyrics of "There was an old woman ... "
	public static void main(String[] args) {
		verseOne();
		verseTwo();
		verseThree();
		verseFour();
		verseFive();
		verseSix();
		verseSeven();
		System.out.println("There was an old woman who swallowed a horse,");
		System.out.println("She died of course.");
	}

	// Prints out verse one
	public static void verseOne() {
		System.out.println("There was an old woman who swallowed a fly.");
		endOfVerse();
	}



	// Verse two parts - - - - - - - - - - - - - - - - -
	// Prints out verse two
	public static void verseTwo() {
		System.out.println("There was an old woman who swallowed a spider,");
		System.out.println("That wriggled and iggled and jiggled inside her.");
		verseTwoHalf();

	}

	//Creates the first half of verse 2 and will be used to build on further halves
	public static void verseTwoHalf() {
		System.out.println("She swallowed the spider to catch the fly,");
		endOfVerse();
	}
	



	// Verse three parts - - - - - - - - - - - - - - - - -
	// Prints out verse three
	public static void verseThree() {
		System.out.println("There was an old woman who swallowed a bird,");
		System.out.println("How absurd to swallow a bird.");
		verseThreeHalf();

	}

	// Builds on verse two's half
	public static void verseThreeHalf() {
		System.out.println("She swallowed the bird to catch the spider,");
		verseTwoHalf();
	}
	// Divider^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



	// Verse four parts -------------------
	// Prints out verse four
	public static void verseFour() {
		System.out.println("There was an old woman who swallowed a cat,");
		System.out.println("Imagine that to swallow a cat.");
		verseFourHalf();
	}

	// Builds on verse three's half
	public static void verseFourHalf() {
		System.out.println("She swallowed the cat to catch the bird,");
		verseThreeHalf();
	}
	// Divider^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



	// Verse five parts --------------------
	// Prints out verse five
	public static void verseFive() {
		System.out.println("There was an old woman who swallowed a dog,");
		System.out.println("What a hog to swallow a dog.");
		verseFiveHalf();

	}

	// Builds on verse four's half
	public static void verseFiveHalf() {
		System.out.println("She swallowed the dog to catch the cat,");
		verseFourHalf();
	}
	// Divider^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^



	// Verse six parts --------------------
	// Prints out verse six
	public static void verseSix() {
		System.out.println("There was an old woman who swallowed a goat,");
		System.out.println("She just opened her throat to swallow a goat.");
		verseSixHalf();
	}

	// Builds on verse five's half
	public static void verseSixHalf() {
		System.out.println("She swallowed the goat to catch the dog,");
		verseFiveHalf();
	}
	// Divider^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^	



	// Verse seven parts -------------------	
	// Prints out verse seven
	public static void verseSeven() {
		System.out.println("There was an old woman who swallowed a cow,");
		System.out.println("I don't know how she swallowed a cow.");
		verseSevenHalf();
	}

	// Builds on verse six's half
	public static void verseSevenHalf() {
		System.out.println("She swallowed the cow to catch the goat,");
		verseSixHalf();
	}
	// Divider^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^	


	// Prints out end of verses 1-7
	public static void endOfVerse() {
		System.out.println("I don't know why she swallowed that fly,");
		System.out.println("Perhaps she'll die.");
		System.out.println();
	}

}
