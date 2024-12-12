/*
 * RUNI version of the Scrabble game.
 */
public class Scrabble {

	// Note 1: "Class variables", like the five class-level variables declared below,
	// are global variables that can be accessed by any function in the class. It is
	// customary to name class variables using capital letters and underline characters.
	// Note 2: If a variable is declared "final", it is treated as a constant value
	// which is initialized once and cannot be changed later.

	// Dictionary file for this Scrabble game
	static final String DICTIONARY_FILE_PATH = "dictionary.txt";

	// The "Scrabble value" of each letter in the English alphabet.
	// 'a' is worth 1 point, 'b' is worth 3 points, ..., z is worth 10 points.
	static final int[] LETTER_VALUES = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3,
										  1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10 };

	// Number of random letters dealt at each round of this Scrabble game
	static int HAND_SIZE = 10;

	// Maximum number of possible words in this Scrabble game
	static int MAX_WORD_COUNT = 100000;

    // The dictionary array (will contain the words from the dictionary file)
	static String[] WORD_LIST = new String[MAX_WORD_COUNT];

	// Actual number of words in the dictionary (set by the init function, below)
	static int TOTAL_WORD_COUNT;

	// Populates the WORD_LIST array with the lowercase version of all the words read
	// from the DICTIONARY_FILE_PATH, and sets TOTAL_WORD_COUNT to the number of words read from the file.
	public static void initialize() {
		// Declares the variable fileReader to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the given file. Used for reading words from the file.  
		In fileReader = new In(DICTIONARY_FILE_PATH);
        System.out.println("Loading word list from file...");
        TOTAL_WORD_COUNT = 0;
		while (!fileReader.isEmpty()) {
			// Reads the next "token" from the file. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			WORD_LIST[TOTAL_WORD_COUNT++] = fileReader.readString().toLowerCase();
		}
        System.out.println(TOTAL_WORD_COUNT + " words loaded.");
	}

	// Checks if the given word is in the dictionary.
	public static boolean isValidWord(String word) {
		for (int i = 0; i < TOTAL_WORD_COUNT; i++) {

			if (WORD_LIST[i].equals(word)) {
				return true;
			}
		}

		return false;
	}
	
	// Returns the Scrabble score of the given word.
	// If the length of the word equals the length of the hand, adds 50 points to the score.
	// If the word includes the sequence "runi", adds 1000 points to the game.
	public static int calculateWordScore(String word) {

		int score = 0;

        for (int i = 0; i < word.length(); i++) {

			score += LETTER_VALUES[word.charAt(i) - 97];

        }

		score *= word.length();

		if (word.length() == HAND_SIZE) {
			score += 50;
		}

		if (word.contains("runi")) {
			score += 1000;
		}

        return score;
    }


	// Creates a random hand of length (HAND_SIZE - 2) and then inserts
	// into it, at random indexes, the letters 'a' and 'e'
	// (these two vowels make it easier for the user to construct words)
	public static String generateRandomHand() {

		String hand = "";
		
		for (int i = 0; i < HAND_SIZE - 2; i++) {
		
		int randomCharCode = (int) (Math.random() * (122 - 97 + 1)) + 97;
            char randomChar = (char) randomCharCode; 

			hand += randomChar;
		
		}

		int randomPosition1 = (int) (Math.random() * (hand.length() + 1));
         hand = hand.substring(0, randomPosition1) + "a" + hand.substring(randomPosition1);

		int randomPosition2 = (int) (Math.random() * (hand.length() + 1));
         hand = hand.substring(0, randomPosition2) + "e" + hand.substring(randomPosition2);

		return hand;
	}
	
    // Runs a single hand in a Scrabble game. Each time the user enters a valid word:
    // 1. The letters in the word are removed from the hand, which becomes smaller.
    // 2. The user gets the Scrabble points of the entered word.
    // 3. The user is prompted to enter another word, or '.' to end the hand. 
	public static void playScrabbleHand(String hand) {
		int remainingLetters = hand.length();
		int totalScore = 0;
		// Declares the variable in to refer to an object of type In, and initializes it to represent
		// the stream of characters coming from the keyboard. Used for reading the user's inputs.   
		In userInput = new In();
		while (hand.length() > 0) {
			System.out.println("Current Hand: " + MyString.spacedString(hand));
			System.out.println("Enter a word, or '.' to finish playing this hand:");
			// Reads the next "token" from the keyboard. A token is defined as a string of 
			// non-whitespace characters. Whitespace is either space characters, or  
			// end-of-line characters.
			String userWord = userInput.readString();
			
			if (userWord.equals(".")) {
				break;
			}

			if (!isValidWord(userWord)) {
				System.out.println("No such word in the dictionary. Try again.");

            	continue;
			}
			
			int wordPoints = calculateWordScore(userWord);
			totalScore += wordPoints;

			System.out.println(userWord + " earned " + wordPoints + " points. Total score: " + totalScore + " points.");

			hand = MyString.remove(hand, userWord);

		
		}
		if (hand.length() == 0) {
	        System.out.println("Ran out of letters. Total score: " + totalScore + " points");
		} else {
			System.out.println("End of hand. Total score: " + totalScore + " points");
		}
	}

	// Plays a Scrabble game. Prompts the user to enter 'n' for playing a new hand, or 'e'
	// to end the game. If the user enters any other input, writes an error message.
	public static void startScrabbleGame() {
		// Initializes the dictionary
    	initialize();
		// The variable userInput is set to represent the stream of characters 
		// coming from the keyboard. Used for getting the user's inputs.  
		In userInput = new In();

		while(true) {
			System.out.println("Enter n to deal a new hand, or e to end the game:");
			// Gets the user's input, which is all the characters entered by 
			// the user until the user enters the ENTER key.
			String userChoice = userInput.readString();
			//// Replace the following break statement with code
			//// that completes the game playing loop
			break;
		}
	}

	public static void main(String[] args) {
		//// Uncomment the test you want to run
		////testBuildingTheDictionary();  
		////testScrabbleScore();    
		////testCreateHands();  
		////testPlayHands();
		////startScrabbleGame();
	}

	public static void testBuildingTheDictionary() {
		initialize();
		// Prints a few words
		for (int i = 0; i < 5; i++) {
			System.out.println(WORD_LIST[i]);
		}
		System.out.println(isValidWord("mango"));
	}
	
	public static void testScrabbleScore() {
		System.out.println(calculateWordScore("bee"));	
		System.out.println(calculateWordScore("babe"));
		System.out.println(calculateWordScore("friendship"));
		System.out.println(calculateWordScore("running"));
	}
	
	public static void testGenerateRandomHands() {
		System.out.println(generateRandomHand());
		System.out.println(generateRandomHand());
		System.out.println(generateRandomHand());
	}
	public static void testPlayScrabbleHands() {
		initialize();
		//playScrabbleHand("ocostrza");
		//playScrabbleHand("arbffip");
		//playScrabbleHand("aretiin");
	}
}

