/**
 * A library of string functions.
 */
public class MyString {
    public static void main(String args[]) {
        String sampleText = "hello";
        System.out.println(countCharacterOccurrence(sampleText, 'h'));
        System.out.println(countCharacterOccurrence(sampleText, 'l'));
        System.out.println(countCharacterOccurrence(sampleText, 'z'));
        System.out.println(addSpacesBetweenChars(sampleText));
        //// Put your other tests here.
    }

    /**
     * Returns the number of times the given character appears in the given string.
     * Example: countCharacterOccurrence("Center",'e') returns 2 and countCharacterOccurrence("Center",'c') returns 0. 
     * 
     * @param inputString - a string
     * @param targetChar - a character
     * @return the number of times targetChar appears in inputString
     */
    public static int countCharacterOccurrence(String inputString, char targetChar) {

        int occurrenceCount = 0;

        for (int i = 0; i < inputString.length(); i++) {

            if (inputString.charAt(i) == targetChar) {

                occurrenceCount++;
            }
        }
        
        return occurrenceCount;
    }

    /** Returns true if str1 is a subset string str2, false otherwise
     *  Examples:
     *  isSubset("sap","space") returns true
     *  isSubset("spa","space") returns true
     *  isSubset("pass","space") returns false
     *  isSubset("c","space") returns true
     *
     * @param subString - a string
     * @param mainString - a string
     * @return true if subString is a subset of mainString, false otherwise
     */
    public static boolean isSubset(String subString, String mainString) {
         
        if (subString.length() > mainString.length()) {
            return false;
        }

        if (subString.isEmpty()) return true;

        
        for (int i = 0; i < subString.length(); i++) {
            char currentChar = subString.charAt(i); 
            
            if (!mainString.contains(Character.toString(currentChar))) {
                
                return false;
            }
    
            mainString = mainString.replaceFirst(Character.toString(currentChar), "");
        }
    
        return true;
    }

    /** Returns a string which is the same as the given string, with a space
     * character inserted after each character in the given string, except
     * for the last character. 
     * Example: addSpacesBetweenChars("silent") returns "s i l e n t"
     * 
     * @param inputString - a string
     * @return a string consisting of the characters of inputString, separated by spaces.
     */
    public static String addSpacesBetweenChars(String inputString) {

        String spacedString = "";
       
        for (int i = 0; i < inputString.length(); i++) {
            
            spacedString += inputString.charAt(i);

            if (i < inputString.length() - 1) {
                spacedString += " ";
            }
            
        }

        return spacedString;
    }
  
    /**
     * Returns a string of n lowercase letters, selected randomly from 
     * the English alphabet 'a', 'b', 'c', ..., 'z'. Note that the same
     * letter can be selected more than once.
     * 
     * Example: generateRandomLetters(3) can return "zoo"
     * 
     * @param n - the number of letters to select
     * @return a randomly generated string, consisting of 'n' lowercase letters
     */
    public static String generateRandomLetters(int n) {
        
        String randomString = "";
        
        for (int i = 0; i < n; i++) {

            int randomAsciiValue = (int) (Math.random() * (122 - 97 + 1)) + 97;
            char randomLetter = (char) randomAsciiValue; 

            randomString += randomLetter;

        }
        return randomString;
    }

    /**
     * Returns a string consisting of the string str1, minus all the characters in the
     * string str2. Assumes (without checking) that str2 is a subset of str1.
     * Example: excludeCharacters("meet","committee") returns "comit" 
     * 
     * @param str1 - a string
     * @param str2 - a string
     * @return a string consisting of str1 minus all the characters of str2
     */
    public static String excludeCharacters(String str1, String str2) {
    
        
        for (int i = 0; i < str2.length(); i++) {
            char currentChar = str2.charAt(i); 
            
            if (str1.contains(Character.toString(currentChar))) {

                str1 = str1.replaceFirst(Character.toString(currentChar), "");
                
            }
        }
    
        return str1;
    }

    /**
     * Returns a string consisting of the given string, with the given 
     * character inserted randomly somewhere in the string.
     * For example, insertAtRandomPosition("s","cat") can return "scat", or "csat", or "cast", or "cats".  
     * @param ch - a character
     * @param str - a string
     * @return a string consisting of str with ch inserted somewhere
     */
    public static String insertAtRandomPosition(char ch, String str) {
        
         
         int randomIndex = (int) (Math.random() * (str.length() + 1));
         String updatedString = str.substring(0, randomIndex) + ch + str.substring(randomIndex);
         return updatedString;
    }    
}

