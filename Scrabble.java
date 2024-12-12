import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Scrabble {

    // Letter values as provided in the problem
    private static final int[] LETTER_VALUES = {
            1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10
    };

    // The dictionary file for the Scrabble game
    static final String WORDS_FILE = "dictionary.txt";

    // List to hold the dictionary words
    static List<String> DICTIONARY = new ArrayList<>();

    // Initialize the dictionary with words from the file
    public static void init() {
        try {
            Scanner scanner = new Scanner(new File(WORDS_FILE));
            System.out.println("Loading word list from file...");

            while (scanner.hasNext()) {
                DICTIONARY.add(scanner.next().toLowerCase()); // Make all words lowercase
            }
            System.out.println(DICTIONARY.size() + " words loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Error reading dictionary file: " + e.getMessage());
        }
    }

    // Check if a word is in the dictionary
    public static boolean isValidWord(String word) {
        return DICTIONARY.contains(word.toLowerCase());
    }

    // Calculate the score of a word
    public static int wordScore(String word) {
        int score = 0;
        word = word.toLowerCase();

        // Sum the letter scores
        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            score += getLetterValue(letter);
        }

        // Multiply by the word length
        score *= word.length();

        return score;
    }

    // Helper method to get letter value from the array
    private static int getLetterValue(char letter) {
        int index = letter - 'a';
        if (index >= 0 && index < LETTER_VALUES.length) {
            return LETTER_VALUES[index];
        }
        return 0; // Default for non-alphabet characters
    }

    // Create a random hand of 8 letters and include 'a' and 'e'
    public static String createHand() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder hand = new StringBuilder();

        // Generate 6 random letters
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(alphabet.length());
            hand.append(alphabet.charAt(index));
        }

        // Add 'a' and 'e' to the hand
        hand.append('a');
        hand.append('e');

        // Shuffle the hand for randomness
        return shuffle(hand.toString());
    }

    // Shuffle the string
    private static String shuffle(String str) {
        Random rand = new Random();
        StringBuilder shuffled = new StringBuilder(str);
        for (int i = 0; i < shuffled.length(); i++) {
            int j = rand.nextInt(shuffled.length());
            char temp = shuffled.charAt(i);
            shuffled.setCharAt(i, shuffled.charAt(j));
            shuffled.setCharAt(j, temp);
        }
        return shuffled.toString();
    }

    // MyString class for handling the hand of letters
    public static class MyString {
        private Map<Character, Integer> letterCount;

        public MyString(String letters) {
            letterCount = new HashMap<>();
            for (char c : letters.toCharArray()) {
                letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
            }
        }

        // Check if a word can be formed from the letters in the hand
        public boolean canFormWord(String word) {
            Map<Character, Integer> tempCount = new HashMap<>(letterCount);
            for (char c : word.toCharArray()) {
                if (tempCount.getOrDefault(c, 0) <= 0) {
                    return false;
                }
                tempCount.put(c, tempCount.get(c) - 1);
            }
            return true;
        }

        // Remove a word from the hand
        public void removeWord(String word) {
            for (char c : word.toCharArray()) {
                letterCount.put(c, letterCount.get(c) - 1);
            }
        }

        // Return the current letters in the hand
        public String getLetters() {
            StringBuilder letters = new StringBuilder();
            for (Map.Entry<Character, Integer> entry : letterCount.entrySet()) {
                for (int i = 0; i < entry.getValue(); i++) {
                    letters.append(entry.getKey());
                }
            }
            return letters.toString();
        }
    }

    // Play the hand
    public static void playHand() {
        // Create a random hand
        String hand = createHand();
        MyString currentHand = new MyString(hand);
        int score = 0;
        System.out.println("Current Hand: " + hand);

        Scanner scanner = new Scanner(System.in);

        // Loop until user wants to stop
        while (true) {
            System.out.println("Enter a word, or '.' to finish playing this hand:");
            String word = scanner.nextLine().toLowerCase();

            if (word.equals(".")) {
                break;
            }

            // Check if the word can be formed from the current hand and is valid
            if (currentHand.canFormWord(word) && isValidWord(word)) {
                int wordPoints = wordScore(word);
                score += wordPoints;
                currentHand.removeWord(word);
                System.out.println(word + " earned " + wordPoints + " points. Score: " + score);
                System.out.println("Current Hand: " + currentHand.getLetters());
            } else {
                System.out.println("No such word in the dictionary or cannot form from current hand. Try again.");
            }
        }

        System.out.println("End of hand. Total score: " + score);
    }

    // Main method to play the game
    public static void main(String[] args) {
        // Initialize the dictionary
        init();

        Scanner scanner = new Scanner(System.in);  // Declare scanner here
        while (true) {
            System.out.println("Enter 'n' to deal a new hand, or 'e' to end the game:");
            String input = scanner.nextLine();  // Read user input
            if (input.equals("e")) {
                break;  // Exit the loop and end the game
            } else if (input.equals("n")) {
                playHand();  // Start a new hand
            } else {
                System.out.println("Invalid input. Please enter 'n' to deal a new hand or 'e' to end the game.");
            }
        }
        scanner.close();  // Close the scanner at the end
    }
}
