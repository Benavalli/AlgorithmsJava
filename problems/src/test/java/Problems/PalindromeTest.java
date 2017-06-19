package Problems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PalindromeTest {

    Palindrome palindrome;

    @Before
    public void setup() {
        palindrome = new Palindrome();
    }

    @Test
    public void checkTrue() {
        assertTrue(palindrome.isPalindrome("ovo"));
        assertTrue(palindrome.isPalindrome("ovO"));
        assertTrue(palindrome.isPalindrome("AmAma"));
    }

    @Test
    public void checkFalse() {
        assertFalse(palindrome.isPalindrome("abc"));
        assertFalse(palindrome.isPalindrome("1234"));
        assertFalse(palindrome.isPalindrome("hdashduhsaud"));
    }

    @Test
    public void checkInitialFalse() {
        assertFalse(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("a"));
    }

    @Test
    public void checkNUll() {
        assertFalse(palindrome.isPalindrome(null));
    }

}