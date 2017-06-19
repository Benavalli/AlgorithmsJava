package Problems;

public class Palindrome {

    /**
     * This method is used to check with the word is palindrome.
     * This objects are comparable, and the sort is made.
     * The complexity of time is O(1) and space O(N)
     * @param word
     * @return boolean
     */
    public boolean isPalindrome(String word) {

        //return cases
        if(word == null || word.length() < 2) {
            return false;
        }

        int start = 0;
        int end = word.length() - 1;
        boolean result = true;

        String wordUpper = word.toUpperCase();
        //The validation occurs at the beginning and at the end of array
        while(start < end) {
            if(wordUpper.charAt(start) != wordUpper.charAt(end)) {
                result = false;
                break;
            }

            ++start;
            --end;
        }

        return result;
    }
}