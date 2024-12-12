/*
 * A simple game that runs as follows. In each turn, the user is prompted to provide an
 * integer, say n. The program then displays n words, drawn randomly from a given dictionary.
 */
public class Demo {

    // Dictionary file for this game
    static final String DICTIONARY_FILE_PATH = "tinyDictionary.txt";

    // Maximum number of words in the dictionary
    static int MAX_WORDS_COUNT = 100000;

    // Array to store the words from the dictionary file
    static String[] wordList = new String[MAX_WORDS_COUNT];

    // Number of words actually loaded into the dictionary
    static int wordCount;

    // Populates the wordList array with words from the dictionary file
    public static void loadWords() {
        In fileReader = new In(DICTIONARY_FILE_PATH);
        System.out.println("Loading words from file...");
        wordCount = 0;
        while (!fileReader.isEmpty()) {
            wordList[wordCount++] = fileReader.readString().toLowerCase();
        }
        System.out.println(wordCount + " words loaded.");
    }

    // Draws and returns a random word from the wordList
    public static String getRandomWordFromList() {
        int randomIndex = (int) (wordCount * Math.random());
        return wordList[randomIndex];
    }

    // Initializes the wordList and runs the game
    public static void runGame() {
        // Load the dictionary words
        loadWords();
        // Declare the input reader for user interaction
        In userInputReader = new In();

        // Game loop
        while (true) {
            System.out.println("Enter the number of words you'd like to see, or 'e' to exit the game:");
            String userInput = userInputReader.readString();
            if (userInput.equals("e")) {
                break;
            } else {
                // Assuming the input is always a valid integer
                int n = Integer.parseInt(userInput);
                for (int i = 0; i < n; i++) {
                    System.out.println(getRandomWordFromList());
                }
            }
        }
    }

    // Main method that starts the game
    public static void main(String[] args) {
        runGame();
    }
}

